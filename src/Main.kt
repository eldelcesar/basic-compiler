import enums.SyntacticStatusEnum
import java.io.File
import java.io.FileNotFoundException

// All code is available on https://github.com/eldelcesar/basic-compiler
// Example source file at ./resources/Archivo_Fuente.txt

fun main(args: Array<String>) {


    try {
        print("Welcome to the program compiler!\nEnter the file (with path) of the file: \n")
        val input = readLine()!!

        // Run Lex Analyser
        var lex = LexAnalyser()
        var lexResult = lex.runLexAnalysis(input)

        // Prepare output file
        val output = "symbolTable.txt"
        var outFile = File(output)

        // Expect result
        println("Running Lex Analyser on '$input' file...")
        if (lexResult.size > 1) {
            val t1 = "\n––––––––––––––––– TOKENS –––––––––––––––––\n"
            val t2 = "\n\n––––––––––––––––– SYMBOLS –––––––––––––––––\n"
            val t3 = "COMPLETED: SUCCESSFUL LEX ANALYSIS\n"
            val text = t1 + lexResult[0] + t2 + lexResult[1] + t3

            // Write file and show status in terminal
            outFile.writeText(text)
            print(text)

            // Run the Syntactic Analysis
            var syn = SynAnalyser(lexResult[0], lexResult[1])
            var synResult = syn.runSynAnalysis()

            if (syn.getStatus().equals(SyntacticStatusEnum.CORRECT)) {
                // print("\n––––––––––– SYMBOLS ––––––––––––\n" + synResult)
            }

        }
        else {
            val t1 = "\n––––––––––––––––– ERRORS –––––––––––––––––\n"
            val t2 = "ERROR: LEX ANALYSIS FAILED\n"
            val text = t1 + lexResult[0] + t2

            // Write file and show status in terminal
            outFile.writeText(text)
            print(text)
        }
    }
    catch (err: FileNotFoundException){

        print("ERROR: FILE NOT FOUND")
    }
}
