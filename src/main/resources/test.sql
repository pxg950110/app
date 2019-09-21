------患者信息表（patient）
create view patient
as
select
'1' as organization_id,----机构标识
'MZH' as idcard_type,----患者证件类型
visit.VisitNo as idcard_number,----患者证件号
visit.CertificateNo as idcard,----身份证号码
visit.PatientName as name,----姓名
visit.Birthday as birthday,----出生日期
case visit.Sex when '男' then '1' 
		       when '女' then '2' 
		       else '9' end as gender,----性别
case visit.NationName when '汉族' then '01'
                      when '蒙古族' then '02'
                      when '回族' then '03'
                      when '藏族' then '04'
                      when '维吾尔族' then '05'
                      when '苗族' then '06'
                      when '彝族' then '07'
                      when '壮族' then '08'
                      when '布依族' then '09'
                      when '朝鲜族' then '10'
                      when '满族' then '11'
                      when '侗族' then '12'
                      when '瑶族' then '13'
                      when '白族' then '14'
                      when '土家族' then '15'
                      when '哈尼族' then '16'
                      when '哈萨克族' then '17'
                      when '傣族' then '18'
                      when '黎族' then '19'
                      when '傈僳族' then '20'
                      when '佤族' then '21'
                      when '畲族' then '22'
                      when '高山族' then '23'
                      when '拉祜族' then '24'
                      when '水族' then '25'
                      when '东乡族' then '26'
                      when '纳西族' then '27'
                      when '景颇族' then '28'
                      when '柯尔克孜族' then '29'
                      when '土族' then '30'
                      when '达斡尔族' then '31'
                      when '仫佬族' then '32'
                      when '羌族' then '33'
                      when '布朗族' then '34'
                      when '撒拉族' then '35'
                      when '毛难族' then '36'
                      when '仡佬族' then '37'
                      when '锡伯族' then '38'
                      when '阿昌族' then '39'
                      when '普米族' then '40'
                      when '塔吉克族' then '41'
                      when '怒族' then '42'
                      when '乌孜别克族' then '43'
                      when '俄罗斯族' then '44'
                      when '鄂温克族' then '45'
                      when '德昂族' then '46'
                      when '保安族' then '47'
                      when '裕固族' then '48'
                      when '京族' then '49'
                      when '塔塔尔族' then '50'
                      when '独龙族' then '51'
                      when '鄂伦春族' then '52'
                      when '赫哲族' then '53'
                      when '门巴族' then '54'
                      when '珞巴族' then '55'
                      when '基诺族' then '56' 
					  else null end as nation,----民族
visit.TelNo as phone,----患者联系电话
Visit.Address as address,----通讯地址
'-' as company_name,----工作单位名称
'-' as company_address,----工作单位地址
'-' as company_tel,----工作单位电话号码
'-' as occupation_cate,----职业类别代码
'-' as guardian_name,----监护人姓名
'-' as guardian_phone,----监护人联系电话
'-' as guardian_relation,----监护人与患者关系代码
'-' as guardian_address,----监护人联系地址
getdate() as update_time----修改时间
from regist_visit as visit;


------挂号明细表（op_registration）
create view op_registration
as
select
'1' as organization_id,----机构标识
visit.VisitNo as registration_no,----挂号编号
'MZH' as idcard_type,----患者证件类型
visit.VisitNo as idcard_number,----患者证件号
visit.VisitNo as visit_sn,----就诊流水号
visit.VisitNo as clinic_number,----门诊号
case visit.RegistStatus when '9' then '02' else '01' end as retreat_flag,----退号标志
case visit.RegistStatus when '9' then visit.oBillID else visit.ReturnOBillID end as charge_no,----收/退费编号
case visit.RegistStatus when '9' then visit.RegistDate else visit.ReturnDate end as registration_time,----挂/退号时间
case visit.RegistType when  '副主任号' then '102'
    when '简易门诊' then '999'
    when '普通号' then '100'
    when '主任号' then '102' 
		else '999' end as registration_type,----挂号类别
case EmergencyFlag when '1' then '1' else '0' end as emergency_flag,----是否急诊
'0201' as visit_type,----就诊性质
'-' as registration_path,----挂号途径代码
case visit.FeeType when '玉树居民' then '2'
    when '玉树慢病' then '8'
    when '玉树农合' then '3'
    when '玉树职工' then '1'
    when '自费' then '7'
    else '9' end as insurance_type,----保险类型（患者属性）
visit.Dept as office_code,----科室编码
(select DeptName  from G_B_Dept where DeptID=visit.Dept) as office_name,----科室名称
'-' as clinical_medicine_cate,----临床医学类型代码
visit.Doctor as reg_doctor_no,----挂号医生编号
(select Name from dbo.G_B_Worker where WorkerID=visit.Doctor) as reg_doctor_name,----挂号医生姓名
'0' as special_flag,----特需标志
'-' as nonlocal_flag,----外地标志
(select sum(Cost) from obill_invoice where oBillID=visit.oBillID) as total_charge,----挂号总费用
(select sum(Fee) from obill_invoice where oBillID=visit.oBillID) as self_pay,----个人支付诊疗费用
'0' as modify_flag,----修改标志
getdate() as update_time----修改时间
from regist_visit visit;



------门诊就诊明细表(op_visit_detail)
create view op_visit_detail
as
select
'1' as organization_id,----机构标识
visit.VisitNo as visit_no,----就诊明细ID
'MZH' as idcard_type,----患者证件类型
visit.VisitNo as idcard_number,----患者证件号
visit.VisitNo as visit_sn, ----就诊流水号
case visit.FirstVisitFlag when '0' then '1' else '0' end as return_visit_flag,----是否复诊
'-' as clinical_medicine_cate, ----临床医学类型代码
case visit.FeeType when '玉树居民' then '2'
                   when  '玉树慢病' then '8'
                   when '玉树农合' then '3'
                   when   '玉树职工' then '1'
                   when '自费' then '7'
                   else '9' end as patient_attr,----患者属性
case visit.RegistType
  when  '副主任号' then '102'
  when    '简易门诊' then '999'
  when '普通号' then '100'
  when '主任号' then '102' else '999' end as visit_category,----就诊类型
case EmergencyFlag when '1' then '1' else '0' end as emergency_flag,----是否急诊
'0201' as visit_type,----就诊性质
visit.Dept as visit_office_code,----就诊科室编码
(select DeptName from G_B_Dept where visit.Dept=DeptID) as visit_office_name,----就诊科室名称
visit.VisitDate as visit_date,  ---- 门诊就诊日期
visit.RegistDate as reception_time, ----接诊时间
visit.EndVisitDate as finish_visit_time,----完成就诊时间
   visit.Doctor as major_doctor_code,   ---- 主诊医生编号
(select Name from G_B_Worker where WorkerID=visit.Doctor) as major_doctor_name,----主诊医生姓名
'-' as code_type,----诊断代码类型
(select DiagCode from oDoct_Diag where VisitNo=visit.VisitNo and DiagNo='1') as visit_diag_code ,----门诊诊断代码
(select  DiagDesc from oDoct_Diag where VisitNo=visit.VisitNo and DiagNo='1') as visit_diag_name ,----门诊诊断名称
'-' as symptom_code,----证型代码
'-' as symptom_name,   ----证型名称
'-' as therapy_name,----治法名称
'-' as visit_diag_desc,----门诊诊断说明
'-' as main_suit,----主诉
'-' as visit_symp_name,----门诊症状-名称
'-' as symp_diag_code,----门诊症状-诊断代码
'-' as observe,----留观观察
'-' as symptom_desc,----症状描述
'-' as attack_date,----发病日期时间
'-' as systolic,----收缩压
'-' as diastolic,----舒张压
'-' as temp,----体温
'-' as symp_sustained_time,  ----症状持续时间
'0' as modify_flag,----修改标志
'-' as security_level,----密级
getdate() as update_time  ----修改时间
from dbo.regist_visit visit


------门诊处方主表（op_prescription）
create view op_prescription
as
select
'1' as organization_id,----机构标识
convert(varchar(50),odoct_order.VisitNo )+'_'+
convert(varchar(50),odoct_order.OrderNo )+'_'+
convert(varchar(50),odoct_order.OrderSubNo ) as pres_major_no,----处方主ID
'MZH' as idcard_type,----患者证件类型
odoct_order.VisitNo as idcard_number,----患者证件号
odoct_order.VisitNo as visit_sn,----就诊流水号
case odoct_order.OrderClass 
     when 'A' then '0101' --西成药
     when 'B' then '01'   --藏药
     when 'C' then '0102' --中草药
     when 'D' then '03'   --检查
     when 'E' then '02'   --检验
     when 'F'	then '99'   --治疗
     when 'G' then '04'	  --手术
     when 'H' then '99'   --麻醉
	   when 'I' then '99'   --护理
     when 'J' then '99'   --膳食
	   when 'K' then '99'   --床位
	   when 'L' then '99'   --输血
	   when 'M' then '99'   --输氧
	   when 'N' then '99'   --材料
	   when 'O' then '99'   --诊察
	   when 'P' then '99'   --其他
     when 'Z' then '99'   --挂号
     else '99' end as pres_category,----处方大类
'-' as pres_type,----处方类型
convert(varchar(50),odoct_order.VisitNo )+'_'+
convert(varchar(50),odoct_order.OrderNo )+'_'+
convert(varchar(50),odoct_order.OrderSubNo ) as pres_sn,----处方号码
odoct_order.Dept as pres_office_code,----开方科室代码
(select DeptName from G_B_Dept where DeptID=odoct_order.Dept) as pres_office_name,----开方科室名称
odoct_order.Doctor pres_doctor_code,----开方医生编号
(select Name from G_B_Worker where WorkerID=odoct_order.Doctor) as pres_doctor_name,----开方医生姓名
'-' as pres_time,----开方时间
'-' as weight,----患者体重
case odoct_fee.InvoiceClass
    when '01' then '0101' --西药费
    when '02' then '0103'	--中成药
    when '03' then '01'	  --藏药费
    when '04' then '01'   --中草药
    when '05' then '0302' --检查费
    when '06' then '0302' --放射费
    when '07' then '0302' --CT费
    when '08' then '0302' --磁共振
    when '09' then '0302' --B超费
    when '10' then '0302' --彩超费
    when '11' then '0302'	--胃镜费
    when '12' then '0302'	--心电图
    when '13' then '0302'	--脑电图
    when '14' then '0303'	--化验费
    when '15' then '0303' --病理费
    when '16' then '0308' --护理费
    when '17' then '0307' --床位费
    when '18' then '9901' --陪床费
    when '19' then '0305' --手术费
    when '20' then '0313' --治疗费
    when '21' then '0306' --麻醉费
    when '22' then '03'   --注射费
    when '23' then '0309' --输血费
    when '24' then '0310' --输氧费
    when '25' then '03'   --特色治疗
    when '26' then '9901' --取暖费
    when '27' then '9901' --体检费
    when '28' then '0315' --观察费
    when '29' then '0307' --急观床位费
    when '30' then '02'   --材料费
    when '31' then '990q' --甘露费
    when '32' then '0301' --挂号费
    when '33' then '0315' --诊查费
    when '34' then '9902' --工本费
    when '35' then '9901' --煎药费
    when '36' then '03'   --会诊费
    when '37' then '9901' --换药费
    when '38' then '9901' --抢救费
    when '39' then '9901' --试剂费
    when '40' then '9901' --其他
    else '9901' end as charge_type,----费用类型
'-' as wm_diag_code,----西医诊断代码
'-' as wm_diag_name,----西医诊断名称
'-' as tcm_disease_code,----中医病名代码
'-' as tcm_disease_name,----中医病名名称
'-' as tcm_symptom_code,----中医证候代码
'-' as tcm_symptom_name,----中医证候名称
odoct_fee.Cost as pres_money,----处方金额
'' as pres_effect_days,----处方有效天数
odoct_order.OrderText as pres_remark,----处方备注信息
'-' as therapy_name,----治则治法
'-' as arch_org_name,----文档保管的医疗机构名称
'-' as arch_org_address,----文档保管的医疗机构地址
'-' as pres_auditor_no,----处方审核药剂师编号
'-' as pres_auditor_sign,----处方审核药剂师签名
'-' as pres_assigner_no,----处方调配药剂师编号
'-' as pres_assigner_sign,----处方调配药剂师签名
'-' as pres_druggist_no,----处方发药药剂师编号
'-' as pres_druggist_sign,----处方发药药剂师签名
'-' as pres_checker_no,----处方核对药剂师编号
'-' as pres_checker_sign,----处方核对药剂师签名
odoct_order.Duration as tcm_pieces_dosage,----中药饮片剂数
odoct_order.Frequecy as tcm_pieces_freq,----中药饮片煎煮频次
'-' as tcm_decoction_method,----中药饮片煎煮法
'-' as tcm_usage,----中药饮片服法
'-' as tcm_pieces_usage,----中药饮片用药方法
'-' as tcm_pieces_pres,----中药饮片处方
'-' as remark,----备注
'0' as modify_flag,----修改标志
getdate() as update_time----修改时间
from oDoct_Order odoct_order
left join  oDoct_Fee_Detail odoct_fee 
on odoct_order.VisitNo=odoct_fee.VisitNo 
and odoct_order.OrderNo =odoct_fee.OrderNo 
and odoct_order.OrderSubNo=odoct_fee.OrderSubNo;



------门诊药品处方明细表（op_drug_pres_detail）
create view op_drug_pres_detail
as
select
'1' as organization_id,----机构标识
convert(varchar(50),odoct_order.VisitNo )+'_'+
convert(varchar(50),odoct_order.OrderNo )+'_'+
convert(varchar(50),odoct_order.OrderSubNo ) as pres_detail_no,----处方明细ID
'MZH' as idcard_type,----患者证件类型
odoct_order.VisitNo as idcard_number,----患者证件号
odoct_order.VisitNo as visit_sn,----就诊流水号
convert(varchar(50),odoct_order.VisitNo )+'_'+
convert(varchar(50),odoct_order.OrderNo )+'_'+
convert(varchar(50),odoct_order.OrderSubNo ) as pres_major_no,----处方主ID
odoct_order.OrderSubNo as pres_doctor_sn,----处方医嘱序号
odoct_order.OrderNo as pres_doctor_grp,----处方医嘱组号
case odoct_order.OrderClass 
     when 'A' then '01'
     when 'B' then '99'--藏药
     when 'C' then '02' end as treat_item_cate,----诊疗项目类别编码
odoct_order.OrderCode as item_code,----项目代码
odoct_order.OrderText as item_name,----项目名称
'-' as item_category,----项目分类
'-' as item_cate_name,----项目分类名称
'-' as dosage_code,----剂型代码
'-' as drug_spec,----药品规格
'-' as usage_path,----用药途径代码
'-' as use_freq,----使用频次代码
odoct_order.Frequecy as drug_freq,----用药频次
odoct_order.Dosage as dosage,----使用次剂量
odoct_order.DosageUnit as dosage_unit,----使用剂量单位
'-' as dosage_total,----使用总剂量
odoct_order.Administration as tcm_pieces_usage,----中药用药方法
odoct_order.FreqCounter as drug_deliver_counts,----发药数量
odoct_order.FreqIntervalUnit as drug_deliver_unit,----发药数量单位
odoct_order.Duration as medicate_days,----用药天数
'-' as medicine_type,----药物类型
'-' as pres_start_time,----处方开始时间
'-' as pres_end_time,----处方停止时间
odoct_fee.PerformDept as exec_office_code,----发药科室编码
(select DeptName from G_B_Dept where odoct_fee.PerformDept =DeptID) as exec_office_name,----发药科室名称
'-' as pres_executor_code,----发药人编号
'-' as pres_executor_name,----发药人姓名
'-' as pres_exec_time,----发药时间
'-' as pres_detail_remark,----备注
'-' as security_level,----密级
'0' as modify_flag,----修改标志
getdate() as update_time----修改时间
from oDoct_Order odoct_order 
left join  oDoct_Fee_Detail odoct_fee 
on odoct_order.VisitNo=odoct_fee.VisitNo
and odoct_order.OrderNo =odoct_fee.OrderNo
and odoct_order.OrderSubNo=odoct_fee.OrderSubNo
where odoct_order.OrderClass in ('A','B','C');



------门诊其他处方明细表（op_other_prescription）
create view op_other_prescription
as
select
'1' as organization_id,----机构标识
convert(varchar(50),odoct_order.VisitNo )+'_'+
convert(varchar(50),odoct_order.OrderNo )+'_'+
convert(varchar(50),odoct_order.OrderSubNo ) as pres_detail_no,----处方明细ID
'MZH' as idcard_type,----患者证件类型
odoct_order.VisitNo as idcard_number,----患者证件号
odoct_order.VisitNo as visit_sn,----就诊流水号
convert(varchar(50),odoct_order.VisitNo )+'_'+
convert(varchar(50),odoct_order.OrderNo )+'_'+
convert(varchar(50),odoct_order.OrderSubNo ) as pres_major_no,----处方主ID
odoct_order.OrderSubNo as YZXH,----处方序号
odoct_order.OrderNo as pres_group,----处方组号
case odoct_order.OrderClass
    when 'D' then '03'  --检查
    when 'E' then '02'  --检验
    when 'F' then '99'  --治疗
    when 'G' then '04'	--手术
    when 'H' then '99'  --麻醉
    when 'I' then '99'  --护理
    when 'J' then '99'  --膳食
    when 'K' then '99'  --床位
    when 'L' then '99'  --输血
    when 'M' then '99'  --输氧
    when 'N' then '99'  --材料
    when 'O' then '99'  --诊察
    when 'P' then '99'  --其他
    when 'Z' then '99'  --	挂号
    else '99' end as treat_item_cate,----诊疗项目类别编码
odoct_order.OrderCode as item_code,----项目代码
'zzzzzzzzzzzzzzz' as item_ins_code,----项目编码（医保 ）
odoct_order.OrderText as item_name,----项目名称
'-' as item_category,----项目分类代码
'-' as item_cate_name,----项目分类名称
odoct_order.Frequecy as exec_freq,----执行频率
'-' as collect_mode,----采集方式
'-' as collect_sample,----采集标本
'-' as inspect_loc,----检查部位
'-' as narcosis_mode,----麻醉方式
'-' as pres_start_time,----处方开始时间
'-' as pres_end_time,----处方停止时间
odoct_fee.PerformDept as exec_office_code,----执行科室编码
(select DeptName from G_B_Dept where odoct_fee.PerformDept =DeptID) as exec_office_name,----执行科室名称
'-' as pres_executor_code,----处方执行人编号
'-' as pres_executor_name,----处方执行人姓名
'-' as pres_exec_time,----处方执行时间
'-' as health_service_req,----卫生服务要求
'-' as other_med_handle,----其它医学处置
'-' as transfer_flag,----转诊标志
'-' as health_eval,----健康问题评估
'-' as visit_org_name,----就诊机构名称
'-' as advisory_issues,----咨询问题
'-' as handle_plan,----处置计划
'-' as recure_guide,----康复措施指导
'-' as other_pres_remark,----备注
'-' as security_level,----密级
'0' as modify_flag,----修改标志
getdate() as update_time----修改时间
from oDoct_Order odoct_order 
left join oDoct_Fee_Detail odoct_fee
on odoct_order.VisitNo=odoct_fee.VisitNo
and odoct_order.OrderNo =odoct_fee.OrderNo
and odoct_order.OrderSubNo=odoct_fee.OrderSubNo
where odoct_order.OrderClass not in ('A','B','C');



------门诊费用明细表(op_charge_detail)
create view op_charge_detail
as
select
'1' as organization_id,----机构标识
convert(varchar(50),fee_detail.VisitNo)+'_'+
convert(varchar(50),fee_detail.OrderNo)+'_'+
convert(varchar(50),fee_detail.OrderSubNo)+'_'+
convert(varchar(50),fee_detail.OrderItemNo) as charge_no,----收费明细ID
'MZH' as idcard_type,----患者证件类型
fee_detail.VisitNo as idcard_number,----患者证件号
fee_detail.VisitNo as visit_sn,----就诊流水号
visit.ReturnOBillID as return_charge_no,----退费号码
case when visit.ReturnOBillID is not null then '1' else '2' end as return_charge_flag ,----退费标志
'-' as charge_scene_flag,----收费场景代码
case when odoct_order.OrderClass in ('A','B','C') then '药品处方明细' else '其他处方明细' end as pres_detail_type,----处方明细类型
convert(varchar(50),fee_detail.VisitNo)+'_'+
convert(varchar(50),fee_detail.OrderNo)+'_'+
convert(varchar(50),fee_detail.OrderSubNo) as pres_detail_no,----处方明细ID
fee_detail.PriceClass as charge_item_type,----收费项目类别代码
(select ClassName from G_B_MR_Fee_Class where ClassCode =fee_detail.PriceClass) as charge_item_name,----收费项目名称
fee_detail.PriceClass as inh_charge_item_type,----医院内部收费项目类别名称
fee_detail.InvoiceClass as charge_classify_code,----费用收入归类代码
(select ClassName from dbo.G_B_obill_invoice_class where ClassCode=fee_detail.InvoiceClass) as charge_classify_name,----费用收入归类名称
visit.RegistDate as charge_time,----费用发生时间
obill_master.oBillID as settlement_no,----费用结算ID
obill_master.FeeDate as settlement_time,----费用结算时间
odoct_order.dept as billing_office_code,----开单科室编码
(select DeptName from G_B_Dept where DeptID =odoct_order.Dept) as billing_office_name,----开单科室名称
odoct_order.Doctor as billing_doctor_code,----开单医生编号
(select Name from dbo.G_B_Worker where WorkerID=odoct_order.Doctor) as billing_doctor_name,----开单医生姓名
fee_detail.PerformDept as exec_office_code,----执行科室编码
(select DeptName from G_B_Dept where DeptID =fee_detail.PerformDept) as exec_office_name,----执行科室名称
obill_master.OperatorNo as executor_code,----执行人员编号
(select G_B_Worker.Name from dbo.G_B_Worker  where WorkerID=obill_master.OperatorNo) as executor_name,----执行人员姓名
fee_detail.PriceCode as detail_item_code,----明细项目代码
fee_detail.PriceName as detail_item_name,----明细项目名称
'-' as detail_item_batchno,----明细项目批次
fee_detail.Unit as detail_item_unit,----明细项目单位
fee_detail.Price as detail_item_price, ----明细项目单价
'-' as item_category,----项目分类代码
'-' as item_cate_name,----项目分类名称
fee_detail.Amount as detail_item_counts,----明细项目数量
fee_detail.Cost as detail_item_receivable,----明细项目应收金额
fee_detail.Fee as detail_item_receipt,----明细项目实收金额
'0' as modify_flag,----修改标志
getdate() as update_time ----修改时间
from oDoct_Fee_Detail fee_detail  
left join dbo.oDoct_Order odoct_order 
on fee_detail.VisitNo=odoct_order.VisitNo 
and fee_detail.OrderNo=odoct_order.OrderNo
and fee_detail.OrderSubNo=odoct_order.OrderSubNo
left join  dbo.regist_visit visit on visit.VisitNo=fee_detail.VisitNo
left join  dbo.obill_master obill_master on obill_master.oBillID=visit.oBillID;




------门诊结算明细表（op_settlement）
create view op_settlement
as
select
'1' as organization_id,----机构标识
obill_master.oBillID as settlement_no,----结算记录ID
'MZH' as idcard_type,----患者证件类型
visit.VisitNo as idcard_number,----患者证件号
visit.VisitNo as visit_sn,----就诊流水号
(select (STUFF((SELECT '|'+InvoiceNo FROM obill_invoice WHERE oBillID = obill.oBillID FOR XML PATH('')),1,1,'')) from obill_invoice obill where oBillID =obill_master.oBillID group by oBillID) as settle_invoice_no,----结算发票号
'-' as charge_scene_flag,----收费场景代码
case when (select sum(fee) from obill_payment where oBillID=obill_master.oBillID)>0 then '1' else '2' end as charge_state,----记录收费状态
obill_master.FeeDate as charge_time ,--费用结算时间
case obill_master.FeeType 
	     when '玉树居民' then '0203'
       when '玉树慢病' then '99'
       when '玉树农合' then '0303'
       when '玉树职工' then '0103'
       when '自费' then '07'
       else '99' end as patient_source,----患者来源属性
case obill_master.FeeType 
       when '玉树居民' then '02'
       when '玉树慢病' then '08'
       when '玉树农合' then '03'
       when '自费' then '07'
       else '99' end as medical_pay_method,----医疗付费方式代码
(select sum(Cost) from obill_invoice obill where oBillID =obill_master.oBillID) as charge_total,--费用结算总金额
(select sum(Fee) from obill_invoice obill where oBillID =obill_master.oBillID) as self_pay,----个人承担费用金额	
obill_master.OperatorNo  as billing_staff_no,----结算人员编号
(select G_B_Worker.Name from G_B_Worker where obill_master.OperatorNo=WorkerID ) as billing_staff_name,----结算人员姓名
'0' as modify_flag,----修改标志
getdate() as update_time----修改时间
from obill_master obill_master
left join regist_visit visit 
on obill_master.oBillID=visit.oBillID
where visit.VisitNo<>'';



------门诊结算支付方式明细表（op_pay_mode_detail）
create view op_pay_mode_detail
as
select
'1' as organization_id,----机构标识
convert(varchar(50),payment.oBillID)+'_'+
convert(varchar(50),payment.PaymentNo) as pay_mode_detail_no,----支付方式记录ID
'MZH' as idcard_type, ----患者证件类型
visit.VisitNo as idcard_number,----患者证件号
visit.VisitNo as visit_sn,----   就诊流水号
payment.oBillID as settlement_no, ----结算记录ID
case when fee>=0 then '1' else '2' end as charge_state,----记录收费状态
case obill_master.FeeType when '玉树居民' then '02'
     when '玉树慢病' then '08'
     when '玉树农合' then '03'
     when '玉树职工' then   '01'
     when '自费' then '07'
     else '99' end as pay_method_code,----支付方式代码
payment.Fee as charge,----支付金额
obill_master.FeeDate as charge_time,----费用结算时间
obill_master.OperatorNo as billing_staff_no,----结算人员编号
(select name from G_B_Worker where obill_master.OperatorNo=WorkerID) as billing_staff_name,----结算人员姓名
'0' as modify_flag,----修改标志
getdate() as update_time----修改时间
from dbo.obill_payment payment
left join obill_master obill_master on payment.oBillID=obill_master.oBillID
left join regist_visit visit on payment.oBillID=visit.oBillID
where visit.VisitNo<>'';



------门诊诊断明细表(op_diagnose_detail)
create view op_diagnose_detail
as
select
'1' as organization_id ,----机构标识
convert(varchar(50),diag.VisitNo)+'_'+
convert(varchar(50),diag.DiagNo) as diagnose_no,----诊断ID
'MZH' as idcard_type,---- 患者证件类型
visit.VisitNo as idcard_number,----患者证件号
visit.VisitNo as visit_sn,----就诊流水号
'-' as modify_flag,---- 修改标志
visit.RegistDate as diag_time,----诊断日期时间
'-' as diag_method,----诊断方法代码
'-' as diag_stand,----诊断标准代码
'2' as diag_category,----诊断类别代码
'-' as symptom_cate,----病证区别代码
'-' as malady_disease_id,
case when  diag.DiagNo='1' then '01' else '02' end as  diag_major_code,----诊断主次代码
diag.DiagNo as diag_order,----诊断顺序
diag.DiagCode as diag_code,----诊断代码
diag.DiagDesc as diag_name,----诊断名称
'-' as suspect_diag_flag,----是否疑诊
'-' as diag_type,----诊断类型
getdate() as update_time----修改时间
from oDoct_Diag diag  
left join regist_visit visit on diag.VisitNo=visit.VisitNo
where visit.VisitNo<>'';
