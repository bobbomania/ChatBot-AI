from parseFunction import parser

file = open("trainingData.txt","r")
answers = open("trainingDataAnswers","w")
questions = open("trainingDataQuestions","w")

answersArr = []
questionsArr = []
frequenciesToken = []

processedAnswers = [[]]

lineNb = 0
index = -1

for line in file.readlines():
    lineNb += 1
    
    if lineNb%2 == 0:
        answersArr.append(line)
    
    else:
        questionsArr.append(line)
        


for token in tokenisedText:
    for question in questionsArr:
        frequency = 0
        
        if token in question:
            
            questionParsed = parser()
            questionParsed.text = question
            frequency = 1/(len(question.parse())-1)
            frequenciesToken.append(frequency)

for answer in answersArr:
    index += 1
    processedAnswers.append([answer,frequenciesToken[index]])
    
print(processedAnswers)