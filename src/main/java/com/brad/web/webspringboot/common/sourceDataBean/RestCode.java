package com.brad.web.webspringboot.common.sourceDataBean;

public enum RestCode {
	SUCCESS("200"),				 		    //成功
	PARAMETER_LOST("10001"),              // 参数丢失
    PARAMETER_TYPE_ERROR("10002"),        // 参数类型错误
    METHOD_NOT_SUPPORTED("10003"),        // 请求方法不支持 405
    JSONCONVERT("10004"),                 // 数据格式不正确 415
    PLEASE_LOGIN("10005"),                // 请登录 403
    UNAUTHORIZED("10006"),                // 没有权限 403
    URL_ERROR("10007"),                   // URL 格式错误 
    SYSTEM_IO("10008"),                   // httpClient 调用错误
    SQL_ERROR("10014"),                          // sql错误
    SERVER_ERROR("10015"),                       // 服务器错误
    NOT_FOUNT("10016"),                   // URL 404
    PARAMETER_VALID("10009"),         // 传递参数验证错误
    FIELD_EMPTY("10010"),               //该字段存在
    FIELD_INVALID("10011"),				 //无效字段 （null）
    FAILED("10012"),				 		//失败
    CODE_NULL("10013"),				 //设备编号不存在
    RFID_NULL("10014"),				 	//rfid不存在
    PIG_INFO_NULL("10015"),				 	//猪只信息未找到
    ;                          

    public String code;

    private RestCode(String code) {
        this.code = code;
    }
}
