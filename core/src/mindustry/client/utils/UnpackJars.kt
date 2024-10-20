package mindustry.client.utils

import arc.*
import arc.files.*

class UnpackJars {
    fun unpack() {
        val jars = arrayOf("bcprov-jdk15on.jar", "bcpkix-jdk15on.jar", "bctls-jdk15on.jar", "bcutil-jdk15on.jar")
        val outputDir = Fi(this::class.java.protectionDomain.codeSource.location.toURI().path).parent()

        for (fi in jars) {
            println("Unloading $fi")
            val output = outputDir.child(fi)
            if (!output.exists()) {
                val inp = Fi(fi, Files.FileType.classpath)
                if (!inp.exists()) return
                inp.copyTo(output)
//                val inp = this::class.java.getResourceAsStream("/$fi") ?: return
//                output.writeBytes(inp.readBytes())
//                inp.close()
            }
        }
    }

    fun unpackSteamUninstaller(): Fi { // Only run as needed since it just adds more work
        val out = Fi(this::class.java.protectionDomain.codeSource.location.toURI().path).sibling("fooUninstaller.jar")
        val inp = Fi("fooUninstaller.jar", Files.FileType.classpath)
        out.delete() // Prevent the file from copying to itself
        inp.copyTo(out)
        return out
    }
}
