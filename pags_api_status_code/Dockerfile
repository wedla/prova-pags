FROM tiangolo/uvicorn-gunicorn-fastapi:python3.7

ENV PORT 8081

RUN curl -fsSLO https://get.docker/builds/Linux/x86_64/docker-17.04.0-ce.tgz \
  && tar xzvf docker-17.04.0-ce.tgz \
  && mv docker/docker /usr/local/bin \
  && rm -r docker docker-17.04.0-ce.tgz
  
RUN pip install pipenv

COPY Pipfile.lock /tmp/Pipfile.lock
COPY Pipfile /tmp/Pipfile

WORKDIR /tmp
RUN pipenv install --system

COPY src/main.py /app
