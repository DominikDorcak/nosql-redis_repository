package sk.upjs.nosql_redis_repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import sk.upjs.nosql_data_source.entity.SimpleStudent;

@Configuration
@EnableRedisRepositories
public class RepositoryConfiguration {

    static String HOSTNAME = "localhost";
    static String PASSWORD = "dh38fhw0238923df89djkd93la9fjs0mq9gjflv9jkddj934df90rj";

    @Bean
    public RedisConnectionFactory connectionFactory() {
        RedisStandaloneConfiguration standaloneConfiguration = new RedisStandaloneConfiguration(HOSTNAME);
//        standaloneConfiguration.setPassword(PASSWORD);
        new LettuceConnectionFactory(standaloneConfiguration);
        LettuceConnectionFactory factory = new LettuceConnectionFactory(standaloneConfiguration);
        factory.afterPropertiesSet();//spring by to mal robit sam, pre istotu to dame este raz
        return factory;
    }
    @Bean
    public RedisTemplate<?, ?> getRedisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<byte[],byte[]> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setDefaultSerializer(new StringRedisSerializer());
//        template.afterPropertiesSet();
        return template;
    }

}
