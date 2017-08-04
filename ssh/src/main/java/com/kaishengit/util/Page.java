package com.kaishengit.util;

public class Page<T> {

    //总条数
    private Long  total;
    //当前页数
    private Integer pageNo;
    //开始条数
    private Integer start;
    //总页数
    private Integer pageTatal;
    //每页显示的数量
    private Integer size;

    private Page<T> items;

       public Page(Long count,Integer pageNo){

           total=count;
          //计算总页数
           pageTatal= Math.toIntExact(total / size);
            if(total%size>0){
                pageTatal++;
            }
            //如果当前页数小于1返回第一页
            if(pageNo<1){
                pageNo=1;
            }
            //最少显示一页
           if(pageTatal==0){
               pageTatal=1;
           }
           //如果当前页数大于总页数返回最后一页
           if(pageNo>pageTatal){
               pageNo=pageTatal;
           }
           //当前页
           this.pageNo=pageNo;
           //计算开始行号
            start=(pageNo-1)*size;


       }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getPageTatal() {
        return pageTatal;
    }

    public void setPageTatal(Integer pageTatal) {
        this.pageTatal = pageTatal;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Page<T> getItems() {
        return items;
    }

    public void setItems(Page<T> items) {
        this.items = items;
    }
}
