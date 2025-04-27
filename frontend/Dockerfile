
FROM node:18-alpine

WORKDIR /app

# Copy package files first to leverage Docker cache
COPY package.json ./

# Install dependencies
RUN npm install

# Copy the rest of the app
COPY . .

# Expose port (React default is 3000)
EXPOSE 3000

# Start the app
CMD ["npm", "run", "dev", "--", "--host", "0.0.0.0"]