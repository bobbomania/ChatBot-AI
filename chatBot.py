from trainingBot import cfd
from parseFunction import parser

questionObj = parser()
file = open("trainingData.txt","r+")
meta = open("meta.txt","r+")

while True:
    questionObj.text = meta.readline()
    tokenisedText = questionObj.parse()
    
    bestAnswer = cfd(tokenisedText)
    #AI bit
    if not bestAnswer[2]:
        meta.truncate(0)
        meta.write(bestAnswer[1])
    
    #google bit
    elif bestAnswer[2]:
        meta.write(bestAnswer[1])