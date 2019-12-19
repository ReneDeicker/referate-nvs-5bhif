let result = {};
const Logger = {
  log(message) {
    console.log(`[LOG] ${message}`);
  },
  setLevel(level) {
    this.level = level;
  },
  level: 'INFO',
};

const items = [1, 'hello', 2, 'foo', 'baa', 'I am a string'];

Logger.log(`LogLevel ${Logger.level}`);

items.forEach((i) => {
  if (typeof i === 'string') {
    result += i;
  } else {
    Logger.log(`Discarded "${i}"`);
  }
});
Logger.setLevel('DEBUG');
Logger.log(`LogLevel ${Logger.level}`);
Logger.log(result);
