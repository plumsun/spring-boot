package com.example.factory;

import org.springframework.lang.NonNull;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

/**
 * CompletableFuture配置超时策略
 *
 * @param <T> the type parameter
 * @author LiHaoHan Created on 2023/7/23
 */
public class CompletableFutures<T> extends CompletableFuture<T> {


    /**
     * Or timeout completable future.
     *
     * @param <T>     the type parameter
     * @param stage   the completion stage
     * @param timeout the timeout
     * @param unit    the unit
     * @return the completable future
     */
    public static <T> CompletableFuture<T> orTimeout(CompletionStage<T> stage, long timeout, TimeUnit unit) {
        final CompletableFuture<T> timeoutFuture = timeoutAfter(timeout, unit);
        // 哪个先完成 就apply哪一个结果
        return timeoutFuture.applyToEither(stage, Function.identity());
    }

    /**
     * Timeout after completable future.
     *
     * @param <T>     the type parameter
     * @param timeout the timeout
     * @param unit    the unit
     * @return the completable future
     */
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
     *
     * @author LiHaoHan
     */
    static final class Delayer {

        private Delayer() {
            throw new IllegalStateException("Utility class");
        }

        /**
         * Delay scheduled future.
         *
         * @param command the command
         * @param delay   the delay
         * @param unit    the unit
         * @return the scheduled future
         */
        static ScheduledFuture<?> delay(Runnable command, long delay,
                                        TimeUnit unit) {
            return delayer.schedule(command, delay, unit);
        }

        /**
         * The type Daemon thread factory.
         *
         * @author LiHaoHan
         */
        static final class DaemonThreadFactory implements ThreadFactory {
            @Override
            public Thread newThread(@NonNull Runnable r) {
                Thread t = new Thread(r);
                t.setDaemon(true);
                t.setName("CompletableFutureDelayScheduler");
                return t;
            }
        }

        /**
         * The Delayer.
         */
        static final ScheduledThreadPoolExecutor delayer;

        static {
            (delayer = new ScheduledThreadPoolExecutor(
                    1, new DaemonThreadFactory())).
                    setRemoveOnCancelPolicy(true);
        }
    }
}
