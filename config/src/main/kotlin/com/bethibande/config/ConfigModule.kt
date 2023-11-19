package com.bethibande.config

/*import com.bethibande.bootstrap.ApplicationModule
import com.bethibande.bootstrap.context.ApplicationContext
import com.bethibande.bootstrap.context.PropertyKey
import com.bethibande.bootstrap.useModule

class ConfigModule: ApplicationModule("Config", true) {

    companion object {

        val KEY_CONFIG_PROVIDER = PropertyKey.of<ConfigProvider>("CONFIG_PROVIDER")

    }

    override fun bootstrap(ctx: ApplicationContext) {
        val provider = ConfigProvider()
        ctx.setProperty(KEY_CONFIG_PROVIDER, provider)
    }
}

fun ApplicationContext.configs(): ConfigProvider = useModule<ConfigModule, ConfigProvider> { _ ->
    return@useModule getProperty(ConfigModule.KEY_CONFIG_PROVIDER)!!
}*/