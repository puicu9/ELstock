package com.elstock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration // 설정용 파일로 사용할게요!
@EnableJpaAuditing // 나 지켜볼거야! 기능
public class AuditConfig {
    // List<> lists = new ArrayList<>(); 처럼 List는 Interface, 오른쪽은 실제로 구현하겠다는~
    @Bean // AuditorAware 구현체를 Bean으로 등록할께요
    public AuditorAware<String> auditorProvider(){
        return new AuditorAwareImpl();

    }
}
