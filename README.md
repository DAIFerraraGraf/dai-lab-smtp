# dai-lab-smtp

### Authors Ferrara Justin, Andrea Graf

## Introduction

The goal of this project is to send fake forged email to a smtp sever. For security and testing resons, the smtp server
will be a docker container.

The command to run the docker container:
```
docker run -d -p 1080:1080 -p 1025:1025 maildev/maildev
```

## Files
The victim's email address and the jokes are stored in different files. The program will read the file and send the
email to the smtp server.
### Emails.txt
The victim's email address are stored in a text file where each line are another email adress.

An example of the email file:
```
jean.dupont@example.com
marie.martin@example.com
pierre.durand@example.com
```
### Content.json
The jokes are stored in a json file. The file contain a single key-value pair. The key is "messages" and the value is an array of objects. 
Each object in the array represents an email message with two properties: "Subject" and "Body". Subjet is the subject of
the email and Body is the content of the email.

An example of the json file:
```
{
    "messages" : [
        {
            "Subject" : "<subject>",
            "Body": "<body>"
        },
        {
            "Subject" : "<subject>",
            "Body": "<body>"
        },
        {
            "Subject" : "<subject>",
            "Body": "<body>"
        }
    ]
}
```

To start the program, run the following command:

Use meaven to compile the program and create the jar file.
```
mvn clean package
```

Run the program with as argument the path to the email file and the path to the json file:
```
java --jar SpammerEmail-1.0-jar-with-dependencies.jar emails.txt content.json
```

To obtains further information about the program, run the following command:
Help:
```
java --jar SpammerEmail-1.0-jar-with-dependencies.jar --help
```
Information about the json file:
```
java --jar SpammerEmail-1.0-jar-with-dependencies.jar --json
```

Example:
```
java --jar SpammerEmail-1.0-jar-with-dependencies.jar ./src/victims.txt ./src/jokes.json
```
