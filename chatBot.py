from trainingBot import cfd
from parseFunction import parser
from internetScraper import maps

questionObj = parser()
file = open("trainingData.txt","r+")
meta = open("meta.txt","r+")

meta = open("meta.txt","r+")

questionObj.text = meta.readline().strip('\n')
tokenisedText = questionObj.parse()
print(questionObj.text)

if "where" in questionObj.text:
    meta.close()
    bestAnswer = maps(questionObj.text)

else:
    bestAnswer = cfd(tokenisedText)[1]
    open('meta.txt', 'w').close()
    
    meta = open("meta.txt","w")
    
    print(bestAnswer)
    meta.write(bestAnswer + '\n')
    
    meta.close()
