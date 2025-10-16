# Deployment Guide

## Full-Stack Clojure Counter App

This is a **complete full-stack application** with:
- **Backend**: Clojure + http-kit + Datomic
- **Frontend**: ClojureScript + Shadow-CLJS

## Local Development

Already documented in README.md - requires running both:
1. Shadow-CLJS compiler (Terminal 1)
2. Clojure backend server (Terminal 2)

## Production Deployment Options

### Option 1: Deploy to a VPS or Cloud Instance

The app requires a JVM environment to run both the backend and serve the frontend.

**Requirements:**
- Java 11+ installed
- Clojure CLI tools installed

**Steps:**
```bash
# Build optimized frontend
clojure -M:dev -m shadow.cljs.devtools.cli release app

# Run production server
clojure -M -m counter.server
```

**Suggested platforms:**
- DigitalOcean App Platform (with Dockerfile)
- Heroku (with Procfile)
- AWS EC2 / Google Cloud Compute
- Railway.app (supports Java)

### Option 2: Docker Deployment

Create a Dockerfile to containerize the entire stack:

```dockerfile
FROM clojure:temurin-21-tools-deps

WORKDIR /app
COPY . .

RUN clojure -M:dev -m shadow.cljs.devtools.cli release app

EXPOSE 3000
CMD ["clojure", "-M", "-m", "counter.server"]
```

### Option 3: Split Deployment (Not Recommended for Demo)

Could split frontend (Vercel/Netlify) and backend (separate service), but this loses the elegance of the full-stack Clojure demo.

## Why This Matters for Interviews

This demonstrates:
1. ✅ Full-stack Clojure/ClojureScript proficiency
2. ✅ Database integration (Datomic)
3. ✅ API design (REST endpoints)
4. ✅ State management (both client and server)
5. ✅ Modern build tooling (Shadow-CLJS)

## Live Demo Recommendation

For showcasing to interviewers:
- **Best**: Deploy to Railway.app or DigitalOcean (keeps full stack together)
- **Alternative**: Run locally during interview and share screen
- **Fallback**: Record a video demo showing all features working

The backend is essential - it shows database persistence and API design!
