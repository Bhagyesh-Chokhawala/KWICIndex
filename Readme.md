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
Do you prefer non case sensitive execution? - It impacts sort order and shifts ignore word.
select appropriate option
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
Do you prefer non case sensitive execution? - It impacts sort order and shifts ignore word.
select appropriate option
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

**dataStructure**:
* **IOPreference** - Singleton data structure to store user's preference as system is collecting input and output both option at start (in order to provide better usability) without waiting for completion of processing.
* **LineStore** - singleton data structure to store provided input lines, generated shifted lines and total for inputlines after reading all inputlines. It also triggers event in each input line it receives.
  
**event**:
* Input received event, the event triggers when the application has a single line available to process.

**framework**:
* Basic Event bus with interfaces to implement event or contract
* Includes resource manager - Singleton object which facilitates reading of configuration from resource property file using dynamic resource loader. 

**handler**:
* Handler for input received event. The handler read the string and trigger instance of IndicesProcessor with ExecutorService to start generating shifted lines for each line parallel.

**KwicIndies**:
* **alphabetizerStrategies** - Facilitates alphabetization using case ignore/ case sensitive sorting. The implementation has been achieved using Strategy Pattern. 
* **inputStrategies** - Facilitates processing of different inputs Console/ File. The implementation has been achieved using Strategy Pattern.
* **ioProcessing** - Facilitates processing of different IO events - Input/Output. The implementation has been achieved using Strategy Pattern and it calls other strategies based on selected IO strategies. 
* **outputFormat** - Facilitates bulding output for different output strategies. This implementation has been achieved using Decorate Pattern. Console output includes plaintext string + footer vs Output file includes plaintext output+ header+ footer.
* **outputStrategies** - Facilitates processing of different Output Console/ File. The implementation has been achieved using Strategy Pattern.
* **processors** - Helper classes which facilitates processing circular shift for words within string including shifts with ignore words processing. It also includes processing of thread through circularshift as and when it reads any line. 
* **MasterController** - Main thread controls overall execution flow.

**resources**:
* Facilitates defining config properties and defining constants for the project.
* Configuration dictionary :
```  
  KwicIndices.threadpool.size - Number of threads will execute in parallel with executorservice.
  KwicIndices.delimiter= delimiter character for shifts, in Unicode.
  KwicIndices.ioOptions= IO options definition ID, displayname and attributes. User will be prompted to provide the attribute. Current implementation has strategy only for file and console IO. Any new IO option need to define new strategy.
  KwicIndices.defaultIOOption= if IO options are not defined what would be default option.
  KwicIndices.caseOptions= Case preference for execution. Strategy has been defined for Case ignore and case sensitive for any new option need to implement strategy. These options will impact sort order and ignore word comparison.
  KwicIndices.inputProcessor= Defines iooption strategy to input processor mapping.
  KwicIndices.outputProcessor= Defines iooption strategy to output processor mapping.
  KwicIndices.AlphabetizerProcessor=Defines case options to alphabetizer processor mapping.
  KwicIndices.shiftIgnoreWords=coma separate list of ignore words. The words are staying in actual case but case options determines execution time consideration.
  KwicIndices.WordToIgnoreStrategies=Defines case options to word to ignore mapping.
  KwicIndices.ConsoleOutputLayout = Defines layout of console output.
  KwicIndices.FileOutputLayout = Defines layout of File output.
 ```
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

