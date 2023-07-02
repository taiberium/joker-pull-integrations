package ru.bcs.perseus.quotes.in.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.bcs.perseus.quotes.model.quotes.Quote;
import ru.bcs.perseus.quotes.service.QuotesService;

@Slf4j
@Component
@RequiredArgsConstructor
public class QuotesKafkaListener {

    private final QuotesService quotesService;

    @KafkaListener(topics = "adapter-quotes")
    public void onQuoteUpdate(final Quote quote) {
        log.debug("Receive quote: {}", quote);
        quotesService.save(quote);
    }
}
