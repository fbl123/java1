package com.kaishengit.service;

import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.kaishengit.dao.CommissionDao;
import com.kaishengit.dao.CustomerDao;
import com.kaishengit.dao.InterestDao;
import com.kaishengit.dao.InvestDao;
import com.kaishengit.dao.ProjectDao;
import com.kaishengit.dao.SettingDao;
import com.kaishengit.entity.Commission;
import com.kaishengit.entity.Customer;
import com.kaishengit.entity.Interest;
import com.kaishengit.entity.Invest;
import com.kaishengit.entity.Project;
import com.kaishengit.entity.Setting;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.util.Page;

public class InvestService {

	InvestDao investDao = new InvestDao();
	InterestDao interestDao = new InterestDao();
	CustomerDao custDao = new CustomerDao();
	SettingDao settingDao = new SettingDao();
	CommissionDao commissionDao = new CommissionDao();
	ProjectDao proDao = new ProjectDao();
	public void addInvest(String custId, String proId,int employeeId, String remainMoney, String investMoney, String rate,
			String month, String signDate, String endDate) {
		
		//6.增加invest 流水
		//	投资者，经办人（登录者），投资金额 ....
		Invest invest = new Invest();
		invest.setProId(Integer.parseInt(proId));
		invest.setCustId(Integer.parseInt(custId));
	
		invest.setEmployeeId(employeeId);
		invest.setInvestMoney(Integer.parseInt(investMoney));
		invest.setMonth(Integer.parseInt(month));
		invest.setRate(Double.parseDouble(rate));
		invest.setSignDate(signDate);
		invest.setEndDate(endDate);
		
		//使用insert方法获得新增的incestid
		int investId = investDao.save(invest);
		
		System.out.println(investId);
		//7.生成N笔利息流水
		//	利息的状态  已领取  未到期  等待领取
		Interest interest = new Interest();
		interest.setCustId(Integer.parseInt(custId));
		interest.setEmployeeId(employeeId);
		interest.setInvestId(investId);
		
		double interestMoney = Double.parseDouble(rate) * Integer.parseInt(investMoney);
		interest.setInterrestmoney(interestMoney);
		insertInterest(interest, month, signDate);
		
		//8.修改客户的积分
		//	100：1的比例
		//1. 根据custid获得customer对象
		Customer cust = custDao.findById(custId);
		
		// 计算新的积分，并更新
		cust.setPoint(cust.getPoint() + (int)(Integer.parseInt(investMoney) * 0.01)); 
		custDao.update(cust);
		
		
		//9.员工佣金
		Commission commission = new Commission();
		commission.setInvestId(investId);
		commission.setEmployeeId(employeeId);
		//计算佣金并入库
		Setting setting = settingDao.findByName("commission");
		double commissionMoney = Integer.parseInt(investMoney) * Double.parseDouble(setting.getValue());
		commission.setCommission(commissionMoney);
		
		commissionDao.save(commission);
		
		// 10.修改项目剩余金额
		//10.1查询proid对应的project对象
		Project pro = proDao.findById(proId);
		//10.2 修改project对象的剩余金额字段
		pro.setRemainMoney(pro.getRemainMoney() - Double.parseDouble(investMoney));
		
		proDao.update(pro);
	}

	/**
	 * @param interest
	 * @param month
	 * @param signDate
	 */
	private void insertInterest(Interest interest, String month,String signDate) {
		for (int i = 1; i <= Integer.parseInt(month); i++) {
			//获得jodatime的格式化的类的对象
			DateTimeFormatter matter = DateTimeFormat.forPattern("yyyy-MM-dd");
			//把String转jodatime对象
			DateTime dt = DateTime.parse(signDate);
			//在该日期对象的基础上增加一个月
			DateTime dt2 = dt.plusMonths(i);
			//通过DateTimeFormatter类的对象把DateTime转成格式化后String
			interest.setInterestsendday(dt2.toString(matter));
			interestDao.save(interest);
		}
		
	}

	public Page<Invest> queryByparam(int pageNo, String key, String value) {
		
		int count = investDao.countByparam(key,value);

		Page<Invest> page = new Page<Invest>(pageNo,count);
		List<Invest> investList = investDao.findListByparam(key,value,page.getStart(),page.getPageSize());
		page.setItems(investList);
		
		return page;
	}

	/**
	 * 根据Id删除投资信息
	 * @param parseInt
	 */
	public void delInvestById(int investId) {
		//1.判断传来的id是否有invest数据存在
		Invest invest = investDao.findById(investId); 
		if(invest != null) {
			//2.判断该投资的利息有没有被领取
			//2.1 查询investid=9 and state = 1 的数量是否>0
			int count = interestDao.countByInvestIdAndState(investId,Interest.INTEREST_STATE_HAD_SEND);
			if(count == 0) {
				//3.删除对应的利息流水
				interestDao.delByIvestId(investId);
				//4.还原客户的积分
				Customer cust = custDao.findById(String.valueOf(invest.getCustId()));	
				int point = cust.getPoint() - (int)(invest.getInvestMoney() * 0.01);
				cust.setPoint(point);
				custDao.update(cust);
				//5.删除对应的员工佣金流水
				commissionDao.delByInvestId(investId);
				//6.还原项目剩余金额  
				//6.1查询proid对应的project对象
				Project pro = proDao.findById(String.valueOf(invest.getProId()));
				//6.2 修改project对象的剩余金额字段
				pro.setRemainMoney(pro.getRemainMoney() + invest.getInvestMoney());
				proDao.update(pro);
				
				//7.删除invest 流水
				investDao.delById(investId);
			}else{
				throw new ServiceException("该投资的利息已经被领取，不能删除");
			}
			
		} else{
			throw new ServiceException("参数异常");
		}
		
		
		
	}

	public void unuseInvestById(String investId) {
//		1.判断传来的id是否有invest数据存在
		Invest invest = investDao.findById(Integer.parseInt(investId)); 
		if(invest != null) {
	//		2.校验签订日期30天以内不能解约
			String signDate = invest.getSignDate();
			DateTime dt = new DateTime();//当前日期2017-06-26
			DateTime dt2 = new DateTime(signDate);
//			DateTime dt2 = new DateTime(2017,6,26,15,53,50,332);
			int days = Days.daysBetween(dt2, dt).getDays();
			if(days > 30) {
				//3.把没有到期的利息流水删除
				interestDao.delByInvestIdAndState(investId,Interest.INTEREST_STATE_NOT_DATE);
				//4.修改invest状态
				invest.setState(Invest.INVEST_STATE_UNUSE);
				investDao.update(invest);
				
			} else{
				throw new ServiceException("签约30天内不可解约");
			}
			
		}else{
			throw new ServiceException("参数异常");
		}
		
		
	}

	/**
	 * 
	 * 通过id获得invest对象
	 * @param id
	 * @return
	 */
	public Invest findInvestById(String id) {
		
		
		return investDao.findById(Integer.parseInt(id));
	}
	
	
}
