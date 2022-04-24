package com.baiyi.security;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

/**
 * @Author: BaiYi
 * @Description: 卖票联系
 * @Date: 2022/4/19 14:49
 */
@Slf4j
public class SellTicket {

    public static void main(String[] args) {
        TicketWindow ticketWindow = new TicketWindow(1000);
        List<Integer> sellAmount = new Vector<>();
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 4000; i++) {
            Thread thread = new Thread(() -> {
                Integer sell = ticketWindow.sell(getRandomAmount());
                sellAmount.add(sell);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    log.error(e.getMessage(), e);
                }
            });
            thread.start();
            threadList.add(thread);
        }

        threadList.forEach(item -> {
            try {
                item.join();
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
        });

        log.debug("sell ticket amount: {}", sellAmount.stream().reduce(Integer::sum).orElse(0));
        log.debug("have ticket: {}", ticketWindow.getCount());
    }

    public static int getRandomAmount() {
        return new Random().nextInt(5);
    }
}

class TicketWindow {
    private Integer count;

    public TicketWindow(Integer count) {
        this.count = count;
    }

    public Integer getCount() {
        return this.count;
    }

    public synchronized Integer sell(int amount) {
        if (this.count >= amount) {
            this.count -= amount;
            return amount;
        }
        return 0;
    }
}
