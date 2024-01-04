# Scripts

## `db-migrate.sh`

This script is used to run database migrations. It is manually used. 

For example, if you need to migrate the database from `2.1.214` to `2.2.224`.

You leave your file inside a directory `2.1.214` and at a higher level this script

```
.
├── 2.1.214
│   └── weather.mv.db
└── db-migrate.sh
```

Run script

```bash
$ ./db-migrate.sh
Exporting database...
Importing data...
weather migrated succesfully
```

Result
```.
├── 2.1.214
│   └── weather.mv.db
├── 2.2.224
│   └── weather.mv.db
└── db-migrate.sh
```
