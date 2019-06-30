package com.bettem.common.utils;


import com.bettem.common.exception.RRException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 短信工具类
 */
public class SMSUtils {

    // 本地异常日志记录对象
    private static final Logger logger = LoggerFactory
            .getLogger(SMSUtils.class);

    /**
     * 短信验证码URL
     */
    private static final String SMS_URL="https://sms.api.ums86.com:9600/sms/Api/Send.do";

    private static final String SP_CODE="250436";
    /**
     * 用户名
     */
    private static final String LOGIN_NAME="admin2";
    /**
     * 密码
     */
    private static final String PASSWORD="bettem123";

    /**
     * 发送短信验证码
     * @param phone
     * @param message
     */
    public static void sendSMSVerifyCode(String phone,String message,String type){
            try{
                HttpClient httpclient = new HttpClient();
                PostMethod post = new PostMethod(SMS_URL);
                post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "gbk");
                post.addParameter("SpCode", SP_CODE);
                post.addParameter("LoginName", LOGIN_NAME);
                post.addParameter("Password", PASSWORD);
                //post.addParameter("MessageContent",new String(smscontext.getBytes("UTF-8"),"GBK"));
                /**
                 * 注册手机验证码
                 */
                if(Constant.SMS_TEMPLATE_LOGIN.equals(type)){
                    message="您的登录验证码为："+message+"，请于15分钟内输入验证，请勿向他人泄露。";
                }else{
                    throw new RRException("您发送的短信没有模板，请重新发送！！");
                }
                post.addParameter("MessageContent", message);
                post.addParameter("UserNumber", phone);
                post.addParameter("SerialNumber", String.valueOf(System.currentTimeMillis()) + "0000000");
                post.addParameter("ScheduleTime", "");
                post.addParameter("ExtendAccessNum", "");
                post.addParameter("f", "1");
                httpclient.executeMethod(post);
                logger.error("短信发送结果：" + post.getResponseBodyAsString());
                String info = new String(post.getResponseBody(), "gbk");
                String b = info.substring(info.indexOf("=") + 1, info.indexOf("&"));
                /**
                 * 判断验证码是否发送成功
                 */
                if(!"0".equals(b)){
                    throw new RRException("发送短信验证码失败，请重新发送！！");
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
    }

    public static void main(String[] args) {
        //SMSUtils.sendSMSVerifyCode("18335461816","888888",Constant.SMS_TEMPLATE_LOGIN);
        String str="aayear,\n" +
                "act_ge_bytearray,\n" +
                "act_ge_property,\n" +
                "act_hi_actinst,\n" +
                "act_hi_attachment,\n" +
                "act_hi_comment,\n" +
                "act_hi_detail,\n" +
                "act_hi_identitylink,\n" +
                "act_hi_procinst,\n" +
                "act_hi_taskinst,\n" +
                "act_hi_varinst,\n" +
                "act_id_group,\n" +
                "act_id_info,\n" +
                "act_id_membership,\n" +
                "act_id_user,\n" +
                "act_re_deployment,\n" +
                "act_re_model,\n" +
                "act_re_procdef,\n" +
                "act_ru_event_subscr,\n" +
                "act_ru_execution,\n" +
                "act_ru_identitylink,\n" +
                "act_ru_job,\n" +
                "act_ru_task,\n" +
                "act_ru_variable,\n" +
                "cgform_button,\n" +
                "cgform_button_sql,\n" +
                "cgform_enhance_js,\n" +
                "cgform_field,\n" +
                "cgform_ftl,\n" +
                "cgform_head,\n" +
                "cgform_uploadfiles,\n" +
                "jform_cgreport_head,\n" +
                "jform_cgreport_item,\n" +
                "t_b_account,\n" +
                "t_b_annual_fund,\n" +
                "t_b_annual_fund_class,\n" +
                "t_b_bank_account,\n" +
                "t_b_benefit_object,\n" +
                "t_b_budget_indicator,\n" +
                "t_b_category_ifms,\n" +
                "t_b_code,\n" +
                "t_b_dept,\n" +
                "t_b_indicator,\n" +
                "t_b_indicator_number_file,\n" +
                "t_b_notice,\n" +
                "t_b_payment_data_info,\n" +
                "t_b_people_count,\n" +
                "t_b_people_extend,\n" +
                "t_b_poverty_people,\n" +
                "t_b_project,\n" +
                "t_b_project_category,\n" +
                "t_b_project_his,\n" +
                "t_b_project_payment,\n" +
                "t_b_project_payment_offline,\n" +
                "t_b_project_perf,\n" +
                "t_b_project_perf_exe,\n" +
                "t_b_project_perf_exed,\n" +
                "t_b_project_perf_self,\n" +
                "t_b_project_perf_selfd,\n" +
                "t_b_project_pero,\n" +
                "t_b_project_refund,\n" +
                "t_b_project_r_category,\n" +
                "t_b_project_screen,\n" +
                "t_b_project_territory,\n" +
                "t_b_p_account,\n" +
                "t_b_p_acthiprocinst,\n" +
                "t_b_p_agreement,\n" +
                "t_b_p_agreement_arch_bonus,\n" +
                "t_b_p_agreement_arch_construct,\n" +
                "t_b_p_agreement_plan,\n" +
                "t_b_p_agreement_proxy,\n" +
                "t_b_p_agreement_report,\n" +
                "t_b_p_agreement_sync_construct,\n" +
                "t_b_p_applyitem,\n" +
                "t_b_p_applyitem_acct,\n" +
                "t_b_p_applyitem_batchloan,\n" +
                "t_b_p_applyitem_detail_info,\n" +
                "t_b_p_applyitem_site,\n" +
                "t_b_p_applysite_collect_pres,\n" +
                "t_b_p_applysite_detail_info,\n" +
                "t_b_p_applysite_relate_pres,\n" +
                "t_b_p_app_file_progress,\n" +
                "t_b_p_app_scatter,\n" +
                "t_b_p_app_scatter_acct,\n" +
                "t_b_p_app_site_acct,\n" +
                "t_b_p_app_site_tender,\n" +
                "t_b_p_assistant_down,\n" +
                "t_b_p_capitaltransferacct,\n" +
                "t_b_p_capital_transfer,\n" +
                "t_b_p_collect_detail_info,\n" +
                "t_b_p_collect_disponse,\n" +
                "t_b_p_collect_rely_in,\n" +
                "t_b_p_county_acct,\n" +
                "t_b_p_fund,\n" +
                "t_b_p_fund_item,\n" +
                "t_b_p_fund_record,\n" +
                "t_b_p_itemoney,\n" +
                "t_b_p_loan_application,\n" +
                "t_b_p_loan_contract,\n" +
                "t_b_p_move_plan,\n" +
                "t_b_p_pay,\n" +
                "t_b_p_poor_livein,\n" +
                "t_b_p_public_account_income,\n" +
                "t_b_p_public_income_acct,\n" +
                "t_b_p_site_complete_check,\n" +
                "t_b_p_site_extend_info,\n" +
                "t_b_p_site_project_progress,\n" +
                "t_b_p_site_project_pro_acct,\n" +
                "t_b_p_spend,\n" +
                "t_b_p_spend_acct,\n" +
                "t_b_p_spend_back,\n" +
                "t_b_p_spend_county,\n" +
                "t_b_p_spend_detail,\n" +
                "t_b_p_usr,\n" +
                "t_b_sso_user,\n" +
                "t_b_task,\n" +
                "t_b_test_business,\n" +
                "t_file_history,\n" +
                "t_p_bpm_file,\n" +
                "t_p_bpm_log,\n" +
                "t_p_city,\n" +
                "t_p_city_role,\n" +
                "t_p_form,\n" +
                "t_p_formpro,\n" +
                "t_p_listener,\n" +
                "t_p_payment_money_transfer,\n" +
                "t_p_process,\n" +
                "t_p_processlistener,\n" +
                "t_p_processnode,\n" +
                "t_p_processnode_function,\n" +
                "t_p_processpro,\n" +
                "t_p_task_url,\n" +
                "t_s_attachment,\n" +
                "t_s_basebus,\n" +
                "t_s_base_user,\n" +
                "t_s_busconfig,\n" +
                "t_s_data_rule,\n" +
                "t_s_data_source,\n" +
                "t_s_depart,\n" +
                "t_s_function,\n" +
                "t_s_icon,\n" +
                "t_s_log,\n" +
                "t_s_muti_lang,\n" +
                "t_s_operation,\n" +
                "t_s_prjstatus,\n" +
                "t_s_quick_opinion,\n" +
                "t_s_role,\n" +
                "t_s_role_function,\n" +
                "t_s_role_org,\n" +
                "t_s_role_user,\n" +
                "t_s_sms,\n" +
                "t_s_sms_sql,\n" +
                "t_s_sms_template,\n" +
                "t_s_sms_template_sql,\n" +
                "t_s_sso_log,\n" +
                "t_s_sso_user,\n" +
                "t_s_table,\n" +
                "t_s_territory,\n" +
                "t_s_territory_join_dept,\n" +
                "t_s_timetask,\n" +
                "t_s_type,\n" +
                "t_s_typegroup,\n" +
                "t_s_user,\n" +
                "t_s_usertype,\n" +
                "t_s_usertype_role,\n" +
                "t_s_user_org,\n" +
                "t_upload_history,\n" +
                "t_b_enterprise_black_list,\n" +
                "t_b_enterprise_info,\n" +
                "t_b_enterprise_permit,\n" +
                "t_b_enterprise_punish,\n" +
                "t_b_enterprise_red_list";
        String tables[]=str.split(",");
        String aa="";
        for(int i=0;i<tables.length;i++){
            String temp=tables[i].replace("\n","");
            aa+="\'"+temp.toUpperCase()+"\'"+",";
        }
        System.out.println(aa);
    }
}
