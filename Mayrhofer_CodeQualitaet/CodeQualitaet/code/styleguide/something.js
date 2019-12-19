var result = new Object()
var Logger = {
    log(message) {
        console.log("[LOG] "+message);
    },
    set_level: function (level) {
        this.level =level;
    },
    level: "INFO"
}

const items = [1,"hello",2,"foo", "baa", 'I am a string'];

Logger.log("LogLevel " + Logger['level'])

for(let i of items)
{
    if(typeof i == 'string'){
        result += i;
    }else
        Logger.log(`Discarded "${i}"`)
    
}
Logger.set_level('DEBUG')
Logger.log("LogLevel " + Logger.level)
Logger.log(result);