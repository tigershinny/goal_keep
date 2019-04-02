package com.adidas.sports.goal.vo

enum class GoalType(val text: String){
    STEP( "step"),
    WALKING_DISTANCE("walking_distance"),
    RUNNING_DISTANCE("running_distance"),
    UNKNOWN("unknown");
    companion object {
        fun from(code: String?): GoalType {
            return when (code){
                "step" -> STEP
                "walking_distance" -> WALKING_DISTANCE
                "running_distance" -> RUNNING_DISTANCE
                else -> UNKNOWN
            }
        }
    }
}
enum class TROPHY(val text: String){
    ZOMBIE_HAND( "zombie_hand"),
    BRONZE_MEDAL("bronze_medal"),
    SILVER_MEDAL("silver_medal"),
    GOLD_MEDAL("gold_medal"),
    UNKNOWN("unknown");
    companion object {
        fun from(code: String?): TROPHY {
            return when (code){
                "zombie_hand" -> ZOMBIE_HAND
                "bronze_medal" -> BRONZE_MEDAL
                "silver_medal" -> SILVER_MEDAL
                "gold_medal" -> GOLD_MEDAL
                else -> UNKNOWN
            }
        }
    }
}


