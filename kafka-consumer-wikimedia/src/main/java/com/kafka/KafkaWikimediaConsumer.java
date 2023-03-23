package com.kafka;

import com.kafka.entity.Wikimedia;
import com.kafka.repository.WikimediaDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaWikimediaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaWikimediaConsumer.class);

    private WikimediaDataRepository wikimediaDataRepository;

    public KafkaWikimediaConsumer(WikimediaDataRepository wikimediaDataRepository) {
        this.wikimediaDataRepository = wikimediaDataRepository;
    }

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeMessage(String eventMessage) {

        LOGGER.info(String.format("Event message received -> %s", eventMessage));

        Wikimedia wikimedia = new Wikimedia();
        wikimedia.setWikiEventData(eventMessage);

        wikimediaDataRepository.save(wikimedia);
    }
}
