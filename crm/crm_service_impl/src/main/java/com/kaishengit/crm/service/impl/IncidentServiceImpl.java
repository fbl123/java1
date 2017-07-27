package com.kaishengit.crm.service.impl;

import com.cronutils.builder.CronBuilder;
import com.cronutils.model.Cron;
import com.cronutils.model.CronType;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.kaishengit.crm.entity.Incident;
import com.kaishengit.crm.mapper.IncidentMapper;
import com.kaishengit.crm.service.IncidentService;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.quartz.jobs.Weixin;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


import static com.cronutils.model.field.expression.FieldExpressionFactory.*;

@Service
public class IncidentServiceImpl implements IncidentService {

    @Autowired
    private IncidentMapper incidentMapper;
    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;


    @Override
    @Transactional
    public void save(Incident incident) {

        incident.setCreateTime(new Date());
        incidentMapper.save(incident);
        //添加定时任务
        notify(incident);
    }

    /**
     * 添加定时任务
     * @param incident
     */
    private void notify(Incident incident) {
        //如果有通知时间则添加

        if(incident.getReminderTime()!=null&&!incident.getState()){
            JobDataMap jobDataMap=new JobDataMap();
            //给account发通知
            jobDataMap.put("account",incident.getAccId());
            //通知内容
            jobDataMap.put("name",incident.getName());
                //创建调度器
            Scheduler scheduler=schedulerFactoryBean.getScheduler();
            //withIdentity JobKey()中的name 确保唯一性 删除用
            JobDetail jobDetail= JobBuilder.newJob(Weixin.class).setJobData(jobDataMap)
                    .withIdentity(new JobKey("account"+incident.getAccId()
                            +incident.getId(), "weixingrounp")).build();

            DateTimeFormatter formatter= DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
            DateTime dateTime=formatter.parseDateTime(incident.getReminderTime());

            //根据日期生成cron表达式
            Cron cron= CronBuilder.cron(CronDefinitionBuilder.instanceDefinitionFor(CronType.QUARTZ))
                    .withYear(on(dateTime.getYear())) //年
                    .withMonth(on(dateTime.getMonthOfYear())) //月
                    .withDoM(on(dateTime.getDayOfMonth()))  //日
                    .withHour(on(dateTime.getHourOfDay()))   //时
                    .withMinute(on(dateTime.getMinuteOfHour()))   //分
                    .withSecond(on(dateTime.getSecondOfMinute()))   //秒
                    .withDoW(questionMark())   //周  （不限制）
                    .instance();
            CronScheduleBuilder scheduleBuilder=CronScheduleBuilder.cronSchedule(cron.asString());
            Trigger trigger=TriggerBuilder.newTrigger().withSchedule(scheduleBuilder).build();
            try {
                scheduler.scheduleJob(jobDetail,trigger);
                scheduler.start();
            } catch (SchedulerException e) {
              throw new ServiceException();
            }

        }
    }

    /**
     * 取消通知
     * @param
     * @param
     * @return
     */
    public void unNotify(Incident incident){

        if(StringUtils.isNotBlank(incident.getReminderTime())){
            Scheduler scheduler=schedulerFactoryBean.getScheduler();
            try {
                scheduler.deleteJob(new JobKey("account"+incident.getAccId()
                        +incident.getId(), "weixingrounp"));
            } catch (SchedulerException e) {
               throw new ServiceException();
            }

        }


    }
    @Override
    public List<Incident> findByAccId(Integer id,String show) {

        return incidentMapper.findByAccId(id,show);
    }

    @Override
    @Transactional
    public void delById(Integer id) {
        Incident incident=incidentMapper.findById(id.toString());
        incidentMapper.delById(id);
        unNotify(incident);
    }

    @Override
    @Transactional
    public void update(Incident incident) {
        incidentMapper.update(incident);
        unNotify(incident);
        notify(incident);
    }

    @Override
    public Incident findById(String id) {
        return incidentMapper.findById(id);
    }

    @Override
    public List<Incident> findByCustId(Integer id) {
        return incidentMapper.findByCustId(id);
    }
}
