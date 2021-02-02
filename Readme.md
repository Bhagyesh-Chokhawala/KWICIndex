- [KWIC Indices](#kwic-indices)
  * [Getting Started](#getting-started)
    + [Prerequisites](#prerequisites)
    + [Running the Project](#running-the-project)
  * [Understanding Code](#understanding-code)
    + [Testing Facts](#testing-facts)
  * [Authors](#authors)
  * [Acknowledgments](#acknowledgments)
# KWIC Indices

Generate KWIC Indexes for given strings. Index is formed by sorting and aligning the words with in a string (except stop word - For this project only non-alphanumeric words are stop words) to be searchable alphabetically in the index.

## Getting Started

If you haven't, you should install the Java SDK (from - https://openjdk.java.net) and Intellij Idea (from - https://www.jetbrains.com/idea/download/ - doesn't have to be an ultimate version) to view the code examples.

If you are new to Intellij, refer - https://www.youtube.com/watch?v=H_XxH66lm3U 

Next off, download the Zip file Assignment01_kwicindices.zip. 

After that, just open the project folder inside IntelliJ IDEA and open the file Assignment01.drawio to refer class diagram.

### Prerequisites

What things you need to install the software and how to install them

```
**System requirements - mac**
    * macOS 10.13 or higher
    * 2 GB RAM minimum, 8 GB RAM recommended
    * 3 GB hard disk space, SSD recommended
    * 1024x768 minimum screen resolution
```
```
**System requirements - window**
    * 64-bit versions of Microsoft Windows 10, 8
    * 2 GB RAM minimum, 8 GB RAM recommended
    * 3 GB hard disk space, SSD recommended
    * 1024x768 minimum screen resolution
```
### Running the Project

Run a jar file - KWICIndex.jar

```
<<java home>>/java -Dfile.encoding=UTF-8 -jar <<browsepath upto KWICIndex.jar>>/KWICIndex.jar

```

Provide preference for Input. The options are not case-sensitive but browse path and file names for file may be case-sensitive depends on runtime OS.

```
Please provide INPUT preference: Choose the option from below list.
Keyin - 'F', For - File
Keyin - 'S', For - Console
<<f or s>>
```
For string preference (F/f) provide the full browse path of plaintext input file.
```
Provide detail for IO method -INPUT, IO Option - File, Attribute - FileName
<<browse path of Plain text file>>
```
For string preference (S/s) provide the input string line by line.
```
Enter lines (terminate input by entering empty line)
<<Enter input lines seperated by Return/Enter key>>
<<terminate input by entering empty line - press enter/return key without entering any string>>
```
For sorting preference CaseIgnore/CaseSensitive Key in Y/N.
```
Prefer match case sort, select appropriate option
Keyin - 'Y', For - CaseIgnore
Keyin - 'N', For - CaseSensitive
<<Y or N>>
```
Provide preference for output. The options are not case-sensitive but browse path and file names for file may be case-sensitive depends on runtime OS.

```
Please provide OUTPUT preference: Choose the option from below list.
Keyin - 'F', For - File
Keyin - 'S', For - Console
<<f or s>>
```
For string preference (F/f) the output will be written in the file.
```
Provide detail for IO method -OUTPUT, IO Option - File, Attribute - FileName
<<browse path of Plain text file>>
```
For string preference (S/s) the output will be displayed on string with debug info
```
No input requires from user for Console Output.
```
Example
```
/Users/Rita/Library/Java/JavaVirtualMachines/openjdk-15.0.2/Contents/Home/bin/java -Dfile.encoding=UTF-8 -jar /Users/Rita/Documents/MS/KWICIndex/out/artifacts/KWICIndex_jar/KWICIndex.jar
Please provide INPUT preference: Choose the option from below list.
Keyin - 'F', For - File
Keyin - 'S', For - Console
f
Provide detail for IO method -INPUT, IO Option - File, Attribute - FileName
/Users/Rita/Documents/MS/KWICIndex/KwicIndices/Input/TextInput.txt
Prefer match case sort, select appropriate option
Keyin - 'Y', For - CaseIgnore
Keyin - 'N', For - CaseSensitive
y
Please provide OUTPUT preference: Choose the option from below list.
Keyin - 'F', For - File
Keyin - 'S', For - Console
s
and if you eat it you will die? 
devil more evil than the
die and if you eat it you will
eat it you will die and if you
evil than the devil more
god What is gooder than
gooder than god What is
have it the poor
if you eat it you will die and
it the poor have
it the rich need
it you will die and if you eat
more evil than the devil,
need it the rich
poor have it the
rich need it the
than god What is gooder
than the devil more evil
What is gooder than god,
you eat it you will die and if
you will die and if you eat it

==========================================================================================
Total Lines in Output : 21
Total execution time: 21

Process finished with exit code 0

```
## Understanding Code
**KwicIndies**:
* **MasterController** - Main thread controls overall execution flow.
* **IOProcessing** - IOProcessing, IOProcessingContext, InputProcessing and OutputProcessing facilitates accepting input and generating output using Strategy Pattern.
* **CircularShift** - It's a helper class that hosts logic to generate shifted lines for any provided single line.
* **IndicesProcessor** - facilitates as a container to execute separate thread for CircularShift processing.
* **Alphabetizer** - Alphabetizer, AlphabetizerContext, CaseIgnoreAlphabetizer and CaseSensitiveAlphabetizer facilite alphabetization using case ignore/ case sensitive sorting.
* **LineStore** - singleton data structure to store provided input lines, generated shifted lines and total for inputlines after reading all inputlines. It also triggers event in each input line it receives.
* **IOPreference** - Singleton data structure to store user's preference as system is collecting input and output both option at start (in order to provide better usability) without waiting for completion of processing.

**framework**:
    - Basic Event bus with interfaces to implement event or contract

**event**:
    - Input received event, the event triggers when the application has a single line available to process.

**handler**:
    - Handler for input received event. The handler read the string and trigger instance of IndicesProcessor with ExecutorService to start generating shifted lines for each line parallel.
### Testing Facts

```
Test Mode - Run the code through Intellij IDE
Maximum file size tested - 1.1 MB, 5814 lines, 37984 shifted lines.
Execution Time - 2.8 sec.
Environment Facts:
    - single CPU core used for testing - 14%
    - Memory - 930 MB
```


## Authors

* **Bhagyesh Chokhawala**

## Acknowledgments

* Thanks to IntelliJ for providing tutorials video, reference to intellij setup made document easy - https://www.youtube.com/channel/UC4ogdcPcIAOOMJktgBMhQnQ

