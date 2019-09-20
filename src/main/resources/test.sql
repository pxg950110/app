select
--     机构标识	organization_id	数字	64	必填	复合主键；医疗机构在采集系统的唯一识别码
-- 收费明细ID	charge_no	字符串	36	必填	复合主键；机构内收费明细的唯一主键
       convert(varchar(50),fee_detail.VisitNo)+'_'+
       convert(varchar(50),fee_detail.OrderNo)+'_'+
       convert(varchar(50),fee_detail.OrderSubNo)+'_'+
       convert(varchar(50),fee_detail.OrderItemNo) as charge_no,
-- 患者证件类型	idcard_type	字符串	16	必填	患者识别码，与患者信息表进行关联
-- 患者证件号	idcard_number	字符串	64	必填
-- 就诊流水号	visit_sn	字符串	36	必填
       fee_detail.VisitNo as visit_sn,
-- 被退费明细ID	return_charge_no	字符串	36	应填	退费记录应该为必填
-- 退费标志	return_charge_flag	字符串	1	必填	1：收费；2：退费；3：被退费；
-- 被退费记录与退费记录相对应
-- 收费场景代码	charge_scene_flag	字符串	1	必填	1：挂号时产生费用；2：门诊收费产生费用
-- 处方明细类型	pres_detail_type	字符串	1	必填	1：药品处方明细；2：其他处方明细
-- 处方明细ID	pres_detail_no	字符串	36	必填	与门诊药品处方明细表或门诊其他处方明细表处方明细ID对应
-- 收费项目类别代码	charge_item_type	字符串	4	必填	填写“诊疗项目类别字典表”代码。
fee_detail.PriceClass as charge_item_type,
-- 收费项目名称	charge_item_name	字符串	500	必填
       (select ClassName from G_B_MR_Fee_Class where  ClassCode =fee_detail.PriceClass),
-- 医院内部收费项目类别名称	inh_charge_item_type	字符串	64	必填
       fee_detail.PriceClass as inh_charge_item_type,
-- 费用收入归类代码	charge_classify_code	字符串	4	必填	填写“费用收入类别字典表”代码
-- 费用收入归类名称	charge_classify_name	字符串	50	必填	填写“费用收入类别字典表”名称
-- 费用发生时间	charge_time	日期时间		必填	费用的发生的时间
-- 费用结算ID	settlement_no	字符串	36	必填	与门诊结算明细表中结算记录ID对应，该明细费用相加必须和门诊结算记录表总费用相等
-- 费用结算时间	settlement_time	日期时间		必填	收费为收费结算时间，退费为退费结算时间
-- 开单科室编码	billing_office_code	字符串	36	必填
odoct_order.dept as billing_office_code,
-- 开单科室名称	billing_office_name	字符串	72	必填
       (select DeptName from G_B_Dept where DeptID =odoct_order.Dept) as billing_office_name,

-- 开单医生编号	billing_doctor_code	字符串	36	必填

-- 开单医生姓名	billing_doctor_name	字符串	50	必填

-- 执行科室编码	exec_office_code	字符串	36	必填
fee_detail.PerformDept as exec_office_code,
-- 执行科室名称	exec_office_name	字符串	72	必填
       (select DeptName from G_B_Dept where DeptID =fee_detail.PerformDept) as exec_office_name,
-- 执行人员编号	executor_code	字符串	36	必填

-- 执行人员姓名	executor_name	字符串	50	必填

-- 明细项目代码	detail_item_code	字符串	36	必填	填写社保代码
       fee_detail.PriceCode as detail_item_code,
-- 明细项目名称	detail_item_name	字符串	64	必填	业务系统实际项目名称
       fee_detail.PriceName as detail_item_name,
-- 明细项目批次	detail_item_batchno	字符串	50	必填

-- 明细项目单位	detail_item_unit	字符串	12	必填
       fee_detail.Unit as detail_item_unit,
-- 明细项目单价	detail_item_price	浮点	12,4	应填	单位：元
       fee_detail.Price as detail_item_price,
-- 项目分类代码	item_category	字符串	32	必填	对于药品处方，填写药物类型，见药物类型字典，如抗生素对应的代码等
-- 项目分类名称	item_cate_name	字符串	64	必填	名称存与代码对应的名称即可
-- 明细项目数量	detail_item_counts	浮点	8,3	应填
       fee_detail.Amount as detail_item_counts,
-- 明细项目应收金额	detail_item_receivable	浮点	12,4	应填	单位：元；收费显示正数，退费显示负数
       fee_detail.Cost as detail_item_receivable,
-- 明细项目实收金额	detail_item_receipt	浮点	12,4	必填	单位：元；收费显示正数，退费显示负数
       fee_detail.Fee as detail_item_receipt,
-- 修改标志	modify_flag	字符串	1	必填	0：正常（应填默认）；1：撤销

-- 修改时间	update_time	日期时间		必填	必须使用医院前置机中的数据库时间
       '-' as update_time
from oDoct_Fee_Detail fee_detail  left join
dbo.oDoct_Order odoct_order on
fee_detail.VisitNo=odoct_order.VisitNo and
fee_detail.OrderNo=odoct_order.OrderNo
and fee_detail.OrderSubNo=odoct_order.OrderSubNo;
