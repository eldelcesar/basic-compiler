import enums.SyntacticStatusEnum
import java.util.*

class SynAnalyser(var tokens: SymbolsTable, var symbolsTable: SymbolsTable) {

    private lateinit var status: SyntacticStatusEnum
    private var lineCounter = 0
    private var charCounter = 0
    private var stack = Stack<Token>()

    // Executes the Syntactic Analysis
    fun runSynAnalysis(): SymbolsTable  {
        var err = false

        for (symbol in symbolsTable.getTable()) {

            lineCounter = symbol.getLn()
            charCounter = symbol.getCn()

            // Syntax Analysis
            when (err) {
                true -> {
                    status = SyntacticStatusEnum.INCORRECT
                    return SymbolsTable()
                }
                false -> {
                    status = SyntacticStatusEnum.CORRECT
                    return symbolsTable
                }
            }

            // Add additional data to symbol table
            // symbol.setIdType()
            // symbol.setValueType()
        }

        return SymbolsTable()
    }

    fun getStatus(): SyntacticStatusEnum{ return status }

}