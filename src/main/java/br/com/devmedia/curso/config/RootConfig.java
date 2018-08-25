package br.com.devmedia.curso.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by Ballem on 07/06/2017.
 */
@Configuration
@ComponentScan("br.com.devmedia.curso")
@EnableWebMvc
public class RootConfig {
}
