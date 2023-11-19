package com.bethibande.bootstrap.context

import com.bethibande.bootstrap.misc.Reflection
import com.bethibande.bootstrap.module.Module

class ApplicationContext(private val modules: Collection<Module>) {

    fun <T: Module> getModule(type: Class<T>): T? = modules.firstOrNull { module -> Reflection.instanceof(module, type) } as? T

    fun <T: Module, R> useModule(type: Class<T>, fn: (T) -> R): R {
        val module = getModule(type)
        if (module == null || !module.initialized()) throw IllegalStateException("The module ${type.simpleName} has not been loaded")

        return fn.invoke(module)
    }

    inline fun <reified T: Module, R> useModule(noinline fn: (T) -> R): R = useModule(T::class.java, fn)
    inline fun <reified T: Module> useModule(noinline fn: (T) -> Unit): Unit = useModule(T::class.java, fn)

}