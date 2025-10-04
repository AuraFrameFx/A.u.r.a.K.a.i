package dev.aurakai.auraframefx.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AgentMemoryDao {
    /**
     * Inserts or replaces an AgentMemoryEntity into the database.
     *
     * @param memory The AgentMemoryEntity to persist; if a row with the same primary key exists it will be replaced.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMemory(memory: AgentMemoryEntity)

    /**
     * Retrieve memories for the specified agent type, ordered newest first.
     *
     * @param agentType The agent type to filter memories by.
     * @return A Flow that emits lists of AgentMemoryEntity matching the given agent type, ordered by `timestamp` descending (newest first).
     */
    @Query("SELECT * FROM agent_memory WHERE agentType = :agentType ORDER BY timestamp DESC")
    fun getMemoriesForAgent(agentType: String): Flow<List<AgentMemoryEntity>>
}