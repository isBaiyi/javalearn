package com.baiyi.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: BaiYi
 * @Description: 使用 interrupt 优雅的打断另外一个线程
 * @Date: 2022/4/12 23:06
 */
@Slf4j
public class TestInterrupt3 {
    public static void main(String[] args) throws InterruptedException {
        TwoPhaseTerminationInterrupt monitor = new TwoPhaseTerminationInterrupt();
        monitor.start();

        Thread.sleep(3500);
        log.debug("main call stop()");
        monitor.stop();
    }
}

@Slf4j
class TwoPhaseTerminationInterrupt {
    private Thread monitor;

    public void start() {
        monitor = new Thread(() -> {
            while (true) {
                boolean interrupted = Thread.currentThread().isInterrupted();
                if (interrupted) {
                    log.debug("料理后事");
                    break;
                }
                try {
                    Thread.sleep(1000);
                    log.debug("执行监控");
                } catch (InterruptedException e) {
                    // 因为线程被打断后，标志位会变成 false，需要执行 interrupt
                    monitor.interrupt();
                    e.printStackTrace();
                }
            }
        });
    }

    public void stop() {
        monitor.interrupt();
    }

}
