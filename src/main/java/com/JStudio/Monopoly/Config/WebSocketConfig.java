package com.JStudio.Monopoly.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic");
		config.setApplicationDestinationPrefixes("/app");
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/hello").withSockJS();
		registry.addEndpoint("/buyNormalField").withSockJS();
		registry.addEndpoint("/buyMiddleField").withSockJS();
		registry.addEndpoint("/buyExtraField").withSockJS();
		registry.addEndpoint("/winOrLoseMoney").withSockJS();
		registry.addEndpoint("/goToJail").withSockJS();
		registry.addEndpoint("/nextPlayer").withSockJS();
	}

}
