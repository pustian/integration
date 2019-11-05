package com.wotung.integration.gym;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * Date: 2017/12/2 14:08
 *
 * @author tianpusen
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Constants {
    public static final String WEB_PACKAGE = "com.wotung.integration.gym.web";
    public static final String SERVICE_PACKAGE = "com.wotung.integration.gym.service";
    public static final String MAPPER_PACKAGE = "com.wotung.integration.gym.mapper";
    public static final String ENTITY_PACKAGE = "com.wotung.integration.gym.entity";

    public static final String CLASSPATH_MAPPER_XML =  "classpath:mapper/*.xml";

    public static final String SERVICE_NAME = "gym";
    public static final String APP_ID = "civil_military";
    public static final String APP_SECRET = "member@wotung.com";
    // 超时时间 1min
    public static final Long EXP_TTL_Millis = 10 * 60 * 1000L;
    // 刷新token时间
    public static final Long Refresh_TTL_Millis = 20 * 60 * 1000L;

}
