package com.qing.constant;

/**
 * 远程访问错误类, 主要定义ERROR CODE
 * 
 * @author guojun
 * @version 创建时间：2013-1-7 下午3:03:08
 */
public class RemoteErrorCode {

    // 数据异常
    public final static String DATA_EXCEPTION = "DATA_EXCEPTION";

    // 数据异常
    public final static String DATA_NULL = "DATA_NULL";

    // 请求为空
    public final static String REQUEST_NULL = "REQUEST_NULL";

    // 登陆名不能为空
    public final static String USER_REQUIRED = "USER_REQUIRED";

    // 密码不能为空
    public final static String PWD_REQUIRED = "PWD_REQUIRED";

    // 机器码不能为空
    public final static String MCN_REQUIRED = "MCN_REQUIRED";

    // 用户不存在
    public final static String USER_NULL = "USER_NULL";

    // PASS WORD ERROR
    public final static String PWD_ERR = "PWD_ERR";

    // 机器与用户绑定错误
    public final static String MACHINE_USER_ERR = "MCN_USR_ERR";

    // 周转箱编号不能为空
    public final static String BOX_REQUIRED = "BOX_REQUIRED";

    // 周转箱状态不能为空
    public final static String BOX_STATUS_REQUIRED = "BOX_STATUS_REQUIRED";

    // 原周转箱不能为空
    public final static String BOX_FROM_NULL = "BOX_FROM_NULL";

    // 目标周转箱不能为空
    public final static String BOX_TO_NULL = "BOX_TO_NULL";

    // 箱号相等错误
    public final static String BOX_EQUALS_ERR = "BOX_EQUALS_ERR";

    // 箱内商户信息为空
    public final static String BOX_CST_NULL = "BOX_CST_NULL";


    // 周转箱不存在
    public final static String BOX_NULL = "BOX_NULL";

    // 客户编号不能为空
    public final static String CST_REQUIRED = "CST_REQUIRED";

    // 笼车编号不能为空
    public final static String CONT_REQUIRED = "CONT_REQUIRED";

    // 笼车状态不能为空
    public final static String CONT_STATUS_REQUIRED = "CONT_STATUS_REQUIRED";

    // 周转箱编号不能为空
    public final static String BOX_DAMAGE_LEVEL_REQUIRED = "BOX_DAMAGE_LEVEL_REQUIRED";

    // 送货数据不能为空
    public final static String DELIVER_NULL = "DELIVER_NULL";

    // 商户编号不能为空
    public final static String CUSTOMCODE_REQUIRED = "CUSTOMCODE_REQUIRED";

    // 送货编号不能为空
    public final static String ROUTECODE_REQUIRED = "ROUTECODE_REQUIRED";

    // 批次号异常
    public final static String ORDERLOT_EXCEPTION = "ORDERLOT_EXCEPTION";

    // 用户名为空
    public final static String USER_NOT_FOUND = "USER_NOT_FOUND";

    // 用户名或密码错误
    public final static String BAD_CEDENTIALS = "BAD_CEDENTIALS";

    // 用户凭证已过期
    public final static String CREDENTIALS_EXPIRED = "CREDENTIALS_EXPIRED";

    // 用户已失效
    public final static String USER_DISABLED = "USER_DISABLED";

    // 用户帐号已过期
    public final static String USER_EXPIRED = "USER_EXPIRED";

    // 用户帐号已被锁定
    public final static String USER_LOCKED = "USER_LOCKED";

    // 用户名不允许为空
    public final static String USERNAME_EMPTY = "USERNAME_EMPTY";

    // 登录密码不能为空
    public final static String LOGINPWD_EMPTY = "LOGINPWD_EMPTY";

    // 原密码不能为空
    public final static String LASTPWD_EMPTY = "LASTPWD_EMPTY";

    // 新密码不能为空
    public final static String NEWPWD_EMPTY = "NEWPWD_EMPTY";

    // 确认密码不能为空
    public final static String CONFIRMPWD_MISTAKE = "CONFIRMPWD_MISTAKE";

    // 当前密码错误
    public final static String PASSWORD_ERROR = "PASSWORD_ERROR";

    // 系统不存在该机器码！
    public final static String MCN_NULL = "MCN_NULL";

    // 系统不存在该笼车！
    public final static String CONT_NULL = "CONT_NULL";

    // 系统不存在该送货组！
    public final static String ROUTE_NULL = "ROUTE_NULL";

    // 系统该送货组已经绑定！
    public final static String ROUTE_BIND = "ROUTE_BIND";

    // 系统该机器码已经绑定！
    public final static String MCN_BIND = "MCN_BIND";

    // 笼车已超出最大装箱数！
    public final static String CONT_FULL = "CONT_FULL";

    // 权限为空
    public final static String PERMIT_NULL = "PERMIT_NULL";

    // 系统已存在该箱号
    public final static String BOX_EXIST_ERR = "BOX_EXIST_ERR";

    // 出库异常
    public final static String CHECKOUT_ERR = "CHECKOUT_ERR";

    // 分拣线编号为空异常
    public final static String LINECODE_ERR = "LINECODE_ERR";
    
    // 该产品当前没有设置返利信息
    public final static String REBATE_MSG_NULL = "REBATE_MSG_NULL";
    
    // 该产品返利信息未到时间或已经过期
    public final static String REBATE_DATE_ERROR = "REBATE_DATE_ERROR"; 

}
