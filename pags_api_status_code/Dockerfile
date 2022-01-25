FROM tiangolo/uvicorn-gunicorn-fastapi:python3.7

ENV PORT 8081

RUN pip install pipenv

COPY Pipfile.lock /tmp/Pipfile.lock
COPY Pipfile /tmp/Pipfile

WORKDIR /tmp
RUN pipenv install --system

COPY src/main.py /app
