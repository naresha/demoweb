package com.nareshak.demo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GreetingMessageProviderTest {

    @Test
    public void greetingMessageShouldbeCorrect() {
        final GreetingMessageProvider greetingMessageProvider = new GreetingMessageProvider();
        final String message = greetingMessageProvider.getMessage();
        assertThat(message).isEqualTo("Hello");
    }
}
