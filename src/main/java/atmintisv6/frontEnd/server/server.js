const https = require('https');
const fs = require('fs');
const path = require('path');
const express = require('express');

const app = express();
const cors = require('cors');

app.use(cors());

const options = {
  key: fs.readFileSync(path.resolve('./private.key')),
  cert: fs.readFileSync(path.resolve('./certificate.crt')),
};

// Your React app build directory
const buildPath = path.resolve(__dirname, 'build');

app.use(express.static(buildPath));

const server = https.createServer(options, app);

const PORT = process.env.PORT || 3000;

server.listen(PORT, () => {
  console.log(`Server is running on https://localhost:${PORT}`);
});