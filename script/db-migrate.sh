src_version=2.1.214
des_version=2.2.224
src_dir=./$src_version
des_dir=./$des_version
username=<user-name>
password=<password>
curl -O https://repo1.maven.org/maven2/com/h2database/h2/$src_version/h2-$src_version.jar
curl -O https://repo1.maven.org/maven2/com/h2database/h2/$des_version/h2-$des_version.jar
for filepath in $src_dir/*.mv.db; do
    dbname=$(basename "$filepath" .mv.db)

    # Export data from old db file to backup.zip
    echo "Exporting database..."
    java -cp h2-$src_version.jar org.h2.tools.Script -url jdbc:h2:$src_dir/$dbname -user $username -password $password
    rm -f $des_dir/$dbname.mv.db

    # Import data from the backup.zip to the new db file
    if [ ! -d "$des_dir" ]; then
        mkdir -p "$des_dir"
    fi
    echo "Importing data..."
    java -cp h2-$des_version.jar org.h2.tools.RunScript -url jdbc:h2:$des_dir/$dbname -user $username -password $password
    rm -f backup.sql
    echo "$dbname migrated succesfully"
done
rm -f h2-$src_version.jar
rm -f h2-$des_version.jar
