package com.bethibande.bootstrap

import com.bethibande.bootstrap.context.ApplicationContext
import com.bethibande.bootstrap.module.Module

object Test {

    class TestModule: Module() {

        fun printHello() = print("Hello")

        override fun ApplicationContext.initialize() {
            logger().info("TestModule starting")
        }
    }

    class AnotherTestModule: Module() {

        fun printName(name: String) = println(" $name!")

        override fun ApplicationContext.initialize() {  }
    }

    @JvmStatic
    fun main(args: Array<String>) = ApplicationBootstrap()
        .withModule(TestModule())
        .withModule(AnotherTestModule())
        .withModule(ApplicationModule {
            useModule<TestModule> { module -> module.printHello() }
            useModule<AnotherTestModule> { module -> module.printName("World") }

            println("Application is running")
        })
        .create()

}