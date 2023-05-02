package io.github.z0r3f.weather.architecture.config

import io.micronaut.context.BeanLocator
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock

class ActionBusConfigTest {

    @Test
    fun `test actionHandlerRegistry bean is correctly configured`() {
        val beanLocatorMock: BeanLocator = mock()
        val config = ActionBusConfig()

        val actionHandlerRegistry = config.actionHandlerRegistry(beanLocatorMock)

        assertNotNull(actionHandlerRegistry, "ActionHandlerRegistry bean should be created")
    }
}

