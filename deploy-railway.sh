#!/bin/bash
# Railway CLI Deployment Script

echo "🚂 Railway Deployment for Clojure Counter"
echo ""
echo "Installing Railway CLI..."
npm i -g @railway/cli

echo ""
echo "Login to Railway..."
railway login

echo ""
echo "Initialize project..."
railway init

echo ""
echo "Deploy!"
railway up

echo ""
echo "✅ Deployment complete! Your app should be live."
echo "Run 'railway open' to view it in browser"
