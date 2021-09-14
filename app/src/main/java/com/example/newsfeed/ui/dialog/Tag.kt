package com.example.newsfeed.ui.dialog

data class Tag(val id: Int, val name: String)

enum class FilterType(val title: String) {
    BREAKINGNEWS("breaking-news"), WORLD( "world"), NATION( "nation"), BUSINESS("business"), TECHNOLOGY("technology"),
    ENTERTAINMENT("entertainment"), SPORTS("sports"), SCIENCE("science"), HEALTH("health")
}

enum class CountryType(val provider: String) {
    TAIWAN("tw"), CHINNA("cn"), JAPAN("jp"),
    HONGKONG("hk"), UNITEDSTATE("us")
}

 val allCategory: List<Tag> = arrayListOf(
     Tag(FilterType.BREAKINGNEWS.ordinal,FilterType.BREAKINGNEWS.title),
     Tag(FilterType.WORLD.ordinal,FilterType.WORLD.title),
     Tag(FilterType.NATION.ordinal,FilterType.NATION.title),
     Tag(FilterType.BUSINESS.ordinal,FilterType.BUSINESS.title),
     Tag(FilterType.TECHNOLOGY.ordinal,FilterType.TECHNOLOGY.title),
     Tag(FilterType.ENTERTAINMENT.ordinal,FilterType.ENTERTAINMENT.title),
     Tag(FilterType.SPORTS.ordinal,FilterType.SPORTS.title),
     Tag(FilterType.SCIENCE.ordinal,FilterType.SCIENCE.title),
     Tag(FilterType.HEALTH.ordinal,FilterType.HEALTH.title)
 )

val allProviders: List<Tag> = arrayListOf(
    Tag(CountryType.TAIWAN.ordinal,CountryType.TAIWAN.provider),
    Tag(CountryType.CHINNA.ordinal,CountryType.CHINNA.provider),
    Tag(CountryType.JAPAN.ordinal,CountryType.JAPAN.provider),
    Tag(CountryType.HONGKONG.ordinal,CountryType.HONGKONG.provider),
    Tag(CountryType.UNITEDSTATE.ordinal,CountryType.UNITEDSTATE.provider)
)
