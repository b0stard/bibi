package com.example.demo.kafka

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class Consumer {

    @KafkaListener(topics = ["demo-topic"], groupId = "demo-group")
    fun listen(message: String) {
        println("Получено сообщение из Kafka: $message")
    }
}