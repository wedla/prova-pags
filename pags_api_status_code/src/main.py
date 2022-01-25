from fastapi import FastAPI, Path, status
from fastapi.responses import JSONResponse

app = FastAPI()

@app.post("/status/{paramCode}")
@app.put("/status/{paramCode}")
@app.get("/status/{paramCode}")
@app.delete("/status/{paramCode}")
def status_code(paramCode: int = Path(..., title="The HTTP status code")):
    if (paramCode < 100 or paramCode > 511):
        return JSONResponse(status_code=status.HTTP_500_INTERNAL_SERVER_ERROR, content=None)
    else:  
        return JSONResponse(status_code=paramCode, content=None)
