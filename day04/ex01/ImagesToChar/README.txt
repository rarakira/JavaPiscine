# Remove target folder
rm -R target

# Create target folder && Compile program setting target as destination folder for .class files
mkdir target && javac -d target src/java/edu.school21.printer/*/*

# Copy resources folder
cp -R src/resources target/.

# Archive compiled files using manifest.txt
jar -cfm ./target/images-to-chars-printer.jar src/manifest.txt -C target .
chmod +x target/images-to-chars-printer.jar

# Execute program through jar
java -jar target/images-to-chars-printer.jar "." "<"
