package io.github.z0r3f.weather.architecture.config

import io.archimedesfw.cqrs.ActionBus
import io.archimedesfw.cqrs.handler.MicronautActionHandlerRegistry
import io.archimedesfw.cqrs.interceptor.HandlerExecutorInterceptor
import io.archimedesfw.cqrs.interceptor.InterceptorActionBus
import io.micronaut.context.BeanLocator
import io.micronaut.context.annotation.Factory
import jakarta.inject.Singleton

@Factory
internal class ActionBusConfig {

    @Singleton
    internal fun actionHandlerRegistry(beanLocator: BeanLocator): MicronautActionHandlerRegistry =
        MicronautActionHandlerRegistry(beanLocator)

    @Singleton
    internal fun actionBus(
        actionHandlerRegistry: MicronautActionHandlerRegistry
    ): ActionBus {
        val executeHandler = HandlerExecutorInterceptor(actionHandlerRegistry)
        return InterceptorActionBus(executeHandler)
    }
}
