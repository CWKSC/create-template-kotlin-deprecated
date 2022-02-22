# CreateTemplate.kt

A Tool for create template

### Usage

```
java -jar CreateTemplateKt-0.0.0.jar
```

### Prerequirement file

Following file under execute directory

```
.create-templateKt/
    src.json
    dist.json
```

The format of `src.json` and `dist.json` is refer to [CascadeJson.kt](https://github.com/CWKSC/CascadeJson.kt)

```json
{
    "src": "key"
}
```

```json
{
    "dist": "key"
}
```

This will convert to a map

The value of those map is a unique key used for concat src and dist file

if src is a directory, it will recursively copy file under directory to dist

### Project environment

Open folder `CreateTemplateKt/` by IntelliJ IDEA

### License

MIT
