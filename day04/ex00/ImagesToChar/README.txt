# Remove target folder
rm -R target

# Create target folder && Compile program setting target as destination folder for .class files
mkdir target && javac -d target src/java/edu.school21.printer/*/*

# Execute program
# java -classpath target edu.school21.printer.app.Program "." "<"
java -classpath target edu.school21.printer.app.Program "." "<" /Users/lbaela/IdeaProjects/JavaPiscineD04/src/ex00/ImagesToChar/src/it.bmp
