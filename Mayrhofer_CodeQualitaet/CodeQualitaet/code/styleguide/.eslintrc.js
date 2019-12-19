module.exports = {
  env: {
    es6: true,
    node: true,
  },
  extends: [
    'airbnb-base',
  ],
  globals: {
    Atomics: 'readonly',
    SharedArrayBuffer: 'readonly',
  },
  parserOptions: {
    ecmaVersion: 2018,
    sourceType: 'module',
  },
  rules: {
    'no-console': 'off',
    'brace-style': ['error', "1tbs"],
    'curly': ['error', 'all'],
    'camelcase': ['error', {
      'properties': 'always',
    }],
    'object-shorthand': ['error', 'always']
  },
};
