package me.fernando.weather.architecture

import jakarta.inject.Inject
import jakarta.inject.Singleton
import mu.KotlinLogging
import java.lang.reflect.ParameterizedType


@Singleton
class PipelineImpl : Pipeline {
    private val logger = KotlinLogging.logger {}

    @Inject
    private lateinit var handlersQuery: List<Handler<*, Query<*>>>
    private val handlersQueryMap = mutableMapOf<String, Handler<*, Query<*>>>()

    override fun <T> dispatch(query: Query<T>): T {
        logger.info("Query: {}", query::class.simpleName)
        if (handlersQueryMap.isEmpty()) populateQueryMap()
        return handlersQueryMap[query::class.qualifiedName]?.invoke(query) as T?
            ?: throw Exception("No handler for query")
    }

    private fun populateQueryMap() {
        handlersQuery.forEach { handlersQueryMap[getClassQueryQualifiedName(it)] = it }
    }

    private fun getClassQueryQualifiedName(handler: Handler<*, Query<*>>): String {
        return (handler.javaClass.genericInterfaces[0] as ParameterizedType).actualTypeArguments[1].typeName
    }

    @Inject
    private lateinit var handlersCommand: List<Handler<*, Command<*>>>
    private val handlersCommandMap = mutableMapOf<String, Handler<*, Command<*>>>()

    override fun <T> dispatch(command: Command<T>): T {
        logger.info("Command: {}", command::class.simpleName)
        if (handlersCommandMap.isEmpty()) populateCommandMap()
        return handlersCommandMap[command::class.qualifiedName]?.invoke(command) as T?
            ?: throw Exception("No handler for command")
    }

    private fun populateCommandMap() {
        handlersCommand.forEach { handlersCommandMap[getClassCommandQualifiedName(it)] = it }
    }

    private fun getClassCommandQualifiedName(handler: Handler<*, Command<*>>): String {
        return (handler.javaClass.genericInterfaces[0] as ParameterizedType).actualTypeArguments[1].typeName
    }
}
