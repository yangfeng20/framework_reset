package com.maple.mqspringboot.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author 杨锋
 * @date 2022/11/9 7:33
 * desc:
 */

@Getter
@Setter
@Configuration
public class ApolloConfig {


    @Value("${[amq.ctag-zcTOK0YT60Pp_7V0dr0XUA]}")
    private List<String> pointConsumer;
}
