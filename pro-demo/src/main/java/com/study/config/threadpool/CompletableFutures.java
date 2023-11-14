package com.study.config.threadpool;

import javax.annotation.Nonnull;
import java.util.concurrent.*;
import java.util.function.Function;

/**
 * CompletableFuture配置超时策略
 *
 * @author LiHaoHan Created on 2023/7/23
 */
public class CompletableFutures<T> extends CompletableFuture<T> {


    public static <T> CompletableFuture<T> orTimeout(CompletableFuture<T> future, long timeout, TimeUnit unit) {
        final CompletableFuture<T> timeoutFuture = timeoutAfter(timeout, unit);
        // 哪个先完成 就apply哪一个结果
        return future.applyToEither(timeoutFuture, Function.identity());
    }

    public static <T> CompletableFuture<T> timeoutAfter(long timeout, TimeUnit unit) {
        CompletableFuture<T> result = new CompletableFuture<>();
        // timeout 时间后 抛出TimeoutException 类似于sentinel / watcher
        Delayer.delayer.schedule(() -> {
            result.completeExceptionally(new TimeoutException("---程序处理超时---"));
        }, timeout, unit);
        return result;
    }

    /**
     * Singleton delay scheduler, used only for starting and * cancelling tasks.
     */
    static final class Delayer {

        private Delayer() {
            throw new IllegalStateException("Utility class");
        }

        static ScheduledFuture<?> delay(Runnable command, long delay,
                                        TimeUnit unit) {
            return delayer.schedule(command, delay, unit);
        }

        static final class DaemonThreadFactory implements ThreadFactory {
            @Override
            public Thread newThread(@Nonnull Runnable r) {
                Thread t = new Thread(r);
                t.setDaemon(true);
                t.setName("CompletableFutureDelayScheduler");
                return t;
            }
        }

        static final ScheduledThreadPoolExecutor delayer;

        static {
            (delayer = new ScheduledThreadPoolExecutor(
                    1, new DaemonThreadFactory())).
                    setRemoveOnCancelPolicy(true);
        }
    }
}
