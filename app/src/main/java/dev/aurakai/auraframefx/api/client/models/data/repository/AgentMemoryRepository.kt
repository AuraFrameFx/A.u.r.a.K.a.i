package dev.aurakai.auraframefx.data.repository

import dev.aurakai.auraframefx.data.room.AgentMemoryDao
import dev.aurakai.auraframefx.data.room.AgentMemoryEntity
import kotlinx.coroutines.flow.Flow

class AgentMemoryRepository(private val dao: AgentMemoryDao) {
    /**
 * Inserts the given agent memory entity into the underlying storage.
 *
 * @param memory The AgentMemoryEntity to persist.
 */
suspend fun insertMemory(memory: AgentMemoryEntity) = dao.insertMemory(memory)
    /**
         * Observe memory entries associated with a specific agent type.
         *
         * @param agentType The agent type identifier whose memories to retrieve.
         * @return A `Flow` that emits lists of `AgentMemoryEntity` belonging to the specified agent type.
         */
        fun getMemoriesForAgent(agentType: String): Flow<List<AgentMemoryEntity>> =
        dao.getMemoriesForAgent(agentType)
}